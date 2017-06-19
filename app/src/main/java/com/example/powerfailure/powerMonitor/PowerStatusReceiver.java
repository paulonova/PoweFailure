package com.example.powerfailure.powerMonitor;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.example.powerfailure.PowerFailureApp;
import com.example.powerfailure.analytics.AppAnalytics;
import com.example.powerfailure.events.PowerStatusEvent;
import com.example.powerfailure.models.BatteryStatus;


/** * Created by Paulo Vila Nova on 13/06/2017.
 */
public class PowerStatusReceiver {
    private BroadcastReceiver powerDisconnectedReceiver = null;
    private BroadcastReceiver powerConnectedReceiver = null;
    private Context context;


    public PowerStatusReceiver() {
        super();
        this.context = PowerFailureApp.getInstance();
        registerPowerDisconnectedReceiver(context);
        registerPowerConnectedReceiver(context);
    }

    public void unregister() {
        unregister(powerConnectedReceiver);
        powerConnectedReceiver = null;
        unregister(powerDisconnectedReceiver);
        powerDisconnectedReceiver = null;
    }
    private void unregister(BroadcastReceiver receiver) {
        if (receiver != null) {
            context.unregisterReceiver(receiver);
        }
    }

    private void registerPowerConnectedReceiver(Context context) {
        if (context == null || powerConnectedReceiver != null) return;
        powerConnectedReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                BatteryStatus status = new BatteryStatus(intent);
                AppAnalytics.getInstance().batteryTracker().powerStatus(status);
                PowerFailureApp.getInstance().post(new PowerStatusEvent(PowerStatusEvent.POWER_CONNECTED,
                                new BatteryStatus(intent)));
            }
        };
        IntentFilter intentPowerFilter = new IntentFilter ();
        intentPowerFilter.addAction("android.intent.action.ACTION_POWER_CONNECTED");
        context.registerReceiver(powerConnectedReceiver, intentPowerFilter);
    }

    private void registerPowerDisconnectedReceiver(Context context) {
        if (context == null || powerDisconnectedReceiver != null) return;

        powerDisconnectedReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                BatteryStatus status = new BatteryStatus(intent);
                AppAnalytics.getInstance().batteryTracker().powerStatus(status);
                // send out notification of power failure
                PowerFailureApp.getInstance()
                        .post(new PowerStatusEvent(PowerStatusEvent.POWER_DISCONNECTED,
                                new BatteryStatus(intent)));
            }
        };

        IntentFilter intentPowerFilter = new IntentFilter ();
        intentPowerFilter.addAction("android.intent.action.ACTION_POWER_DISCONNECTED");
        context.registerReceiver(powerDisconnectedReceiver, intentPowerFilter);
    }
}
