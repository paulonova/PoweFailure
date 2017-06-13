package com.example.powerfailure.ui.fragments;

import android.annotation.TargetApi;
import android.os.Build;

import com.example.powerfailure.R;


/**
 * This fragment shows data and sync preferences only. It is used when the
 * activity is showing a two-pane settings UI.
 *
 * Created by Albert on 9/19/2016.
 *
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class DataSyncPreferenceFragment extends BasePreferenceFragment {
    @Override
    protected int getXmlPreferenceId() {
        return R.xml.pref_data_sync;

    }

    @Override
    protected String[] getPreferenceKey() {
        return new String[] {"sync_frequency"};
    }
}
