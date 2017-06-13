package com.example.powerfailure.ui.fragments;

import android.annotation.TargetApi;
import android.os.Build;

import com.example.powerfailure.R;

/**
 * This fragment shows general preferences only. It is used when the
 * activity is showing a two-pane settings UI.
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class GeneralPreferenceFragment extends BasePreferenceFragment {
    @Override
    protected int getXmlPreferenceId() {
        return R.xml.pref_general;
    }

    @Override
    protected String[] getPreferenceKey() {
        return new String[] {"example_text", "example_list"};
    }
}
