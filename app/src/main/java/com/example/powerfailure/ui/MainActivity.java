package com.example.powerfailure.ui;


import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.example.powerfailure.PowerFailureApp;
import com.example.powerfailure.R;
import com.example.powerfailure.analytics.AppAnalytics;
import com.example.powerfailure.models.BatteryStatus;
import com.example.powerfailure.network.ConnectivityMonitor;
import com.example.powerfailure.powerMonitor.PowerStatusReceiver;

public class MainActivity extends FirebaseActivity {
	private Button startStop, logoutButton;
	private PowerStatusReceiver powerFail =  null;
	private ConnectivityMonitor networkConnection;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(R.layout.activity_main);

		startStop = (Button) findViewById(R.id.start_stop);
		logoutButton = (Button)findViewById(R.id.logout_button);

		//  register a listener for button press
		startStop.setOnClickListener(startListener);
		logoutButton.setOnClickListener(getLogoutListener());

		networkConnection = new ConnectivityMonitor(null);
	}

		
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				// This ID represents the Home or Up button. In the case of this
				// activity, the Up button is shown. Use NavUtils to allow users
				// to navigate up one level in the application structure. For
				// more details, see the Navigation pattern on Android Design:
				//
				// http://developer.android.com/design/patterns/navigation.html#up-vs-back
				//
				NavUtils.navigateUpFromSameTask(this);
				return true;

			case R.id.action_settings:
				Intent settings = new Intent(this, SettingsActivity.class);
				startActivity(settings);
				return true;
		}
		return super.onOptionsItemSelected(item);
	}

	
	// this is part of Android activity lifecycle
	@Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterPowerStatusService();
    }


    private void unregisterPowerStatusService() {
        // stop detecting power failure
        if (powerFail != null) {
            powerFail.unregister();
        }
    }

    private View.OnClickListener stopListener = new View.OnClickListener () {

        /* (non-Javadoc)
         * @see android.view.View.OnClickListener#onClick(android.view.View)
         */
        public void onClick(View v) {
            unregisterPowerStatusService();
			sendBatteryStatus();
            // dynamically re-use same button to re-start power failure monitoring
            startStop.setOnClickListener(startListener);
            startStop.setText("Start");
            powerFail = null;
        }

    };


    private View.OnClickListener startListener = new View.OnClickListener () {

        public void onClick(View arg0) {

            // start listening for the power status
            if (powerFail == null) {
                powerFail = new PowerStatusReceiver();
            }
			sendBatteryStatus();

            // dynamically re- use same button to stop power status monitoring
            startStop.setOnClickListener(stopListener);
            startStop.setText("Stop");
        }
    };

    private void sendBatteryStatus(){
		IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
		Intent batteryStatus = PowerFailureApp.getInstance().registerReceiver(null, ifilter);

		AppAnalytics.getInstance().batteryTracker().batteryStatus(new BatteryStatus(batteryStatus));
	}

}
