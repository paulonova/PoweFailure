package com.example.powerfailure.network;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;

import com.example.powerfailure.PowerFailureApp;
import com.example.powerfailure.models.NetworkConnectionEnum;

import java.util.ArrayList;
import java.util.List;


/** * Created by Paulo Vila Nova on 13/06/2017.
 */
public class ConnectivityMonitor extends BroadcastReceiver{

    private boolean isConnected = false;
    private boolean bounded = false;

    public interface NetworkListener {
        public void onNetworkStateChange(NetworkConnectionEnum networkState);
    }

    private List<NetworkListener> networkListeners;

    public ConnectivityMonitor(NetworkListener listener) {
        super();
        networkListeners = new ArrayList<NetworkListener>();
        bind();
    }

    public void bind() {
        if (!bounded) {
            IntentFilter filter = new IntentFilter();
            filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
            filter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
            filter.setPriority(1000);
            PowerFailureApp.getInstance().registerReceiver(this, filter);
            bounded = true;
        }
        checkIfConnected();
    }

    public void unbind() {
        PowerFailureApp.getInstance().unregisterReceiver(this);
        bounded = false;
    }

    public void addListener(NetworkListener listener) {
        networkListeners.add(listener);
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        boolean noConnection = intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, false);
        if (noConnection) {
            if (isConnected) {
                isConnected = false;
                notifyNetworkStateChange(NetworkConnectionEnum.DISCONNECTED,
                        intent.getStringExtra(ConnectivityManager.EXTRA_REASON));
            }
        } else {
            if (!isConnected) {
                checkIfConnected();
            }
        }
    }


    private void notifyNetworkStateChange(NetworkConnectionEnum state, String reason) {

        for (NetworkListener listener : networkListeners) {
            listener.onNetworkStateChange(state);
        }
    }

    private void dumpNetworkInfo(Intent intent, String tag) {
        Log.i(tag, "action: " + intent.getAction());
        Log.i(tag, "component: " + intent.getComponent());
        Bundle extras = intent.getExtras();
        if (extras != null) {
            for (String key: extras.keySet()) {
                Log.i(tag, "key [" + key + "]: " + extras.get(key));
            }
        }
        else {
            Log.i(tag, "no extras");
        }
    }

    private void checkIfConnected() {
        ConnectivityManager cm = (ConnectivityManager) PowerFailureApp.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if ( (activeNetwork != null) && activeNetwork.isConnected() ) {
            if (!isConnected) {
                isConnected = true;
                notifyNetworkStateChange(NetworkConnectionEnum.CONNECTED, null);
            }
        } else {
            if (isConnected) {
                isConnected = false;
                String reason = activeNetwork != null ? activeNetwork.getReason() : "";
                notifyNetworkStateChange(NetworkConnectionEnum.DISCONNECTED, reason);
            }
        }
    }

    @SuppressWarnings("deprecation")
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static boolean isAirplaneModeOn(Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
            return Settings.System.getInt(context.getContentResolver(),
                    Settings.System.AIRPLANE_MODE_ON, 0) != 0;
        } else {
            return Settings.Global.getInt(context.getContentResolver(),
                    Settings.Global.AIRPLANE_MODE_ON, 0) != 0;
        }
    }

    public NetworkConnectionEnum getNetworkState() {
        checkIfConnected();
        return isConnected ? NetworkConnectionEnum.CONNECTED : NetworkConnectionEnum.DISCONNECTED;
    }
}
