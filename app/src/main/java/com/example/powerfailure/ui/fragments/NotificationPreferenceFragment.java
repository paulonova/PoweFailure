package com.example.powerfailure.ui.fragments;

import android.annotation.TargetApi;
import android.os.Build;

import com.example.powerfailure.R;

/**
 * This fragment shows notification preferences only. It is used when the
 * activity is showing a two-pane settings UI.
 *
 * Created by Albert on 9/19/2016.
 *
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class NotificationPreferenceFragment extends BasePreferenceFragment {

    @Override
    protected int getXmlPreferenceId() {
        return R.xml.pref_notification;
    }

    @Override
    protected String[] getPreferenceKey() {
        return new String[] {"notifications_new_message_ringtone"};
    }
}
