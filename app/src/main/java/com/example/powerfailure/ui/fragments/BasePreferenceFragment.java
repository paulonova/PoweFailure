package com.example.powerfailure.ui.fragments;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.view.MenuItem;

import com.example.powerfailure.ui.PreferenceListener;
import com.example.powerfailure.ui.SettingsActivity;


/**
 * This fragment shows data and sync preferences only. It is used when the
 * activity is showing a two-pane settings UI.
 *
 *  Created by Albert on 9/19/2016.
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public abstract class BasePreferenceFragment extends PreferenceFragment  {
    private PreferenceListener listener;

    protected abstract int getXmlPreferenceId();
    protected abstract String[] getPreferenceKey();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        init(context);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            init(activity);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //addPreferencesFromResource(R.xml.pref_data_sync);
        addPreferencesFromResource(getXmlPreferenceId());
        setHasOptionsMenu(true);

        // Bind the summaries of EditText/List/Dialog/Ringtone preferences
        // to their values. When their values change, their summaries are
        // updated to reflect the new value, per the Android Design
        // guidelines.
        //listener.bindPreferenceSummaryToValue(findPreference("sync_frequency"));
        for (String prefKey : getPreferenceKey()) {
            listener.bindPreferenceSummaryToValue(findPreference(prefKey));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            startActivity(new Intent(getActivity(), SettingsActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void init(Context context) {
        if (context instanceof PreferenceListener) {
            listener = (PreferenceListener) context;
        }
    }
}
