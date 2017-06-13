package com.example.powerfailure.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.powerfailure.PowerFailureApp;

/** * Created by Paulo Vila Nova on 13/06/2017.
 */

public class UserSettings {
    private static final String TAG = UserSettings.class.getSimpleName();
    private String APP_PREFS_FILE = TAG + ".APP_PREFS_FILE";
    private SharedPreferences settings;



    private enum UserPrefKey {
        FIRST_TIME_KEY              ( TAG + ".FIRST_TIME_KEY" ),
        USERNAME_KEY                ( TAG + ".USERNAME_KEY" ),
        PASSWORD_KEY                ( TAG + ".PASSWORD_KEY" ),
        PATIENT_ID_KEY              ( TAG + ".PATIENT_ID_KEY"),
        PREFERENCE_MDS_URL          ( TAG + ".PREFERENCE_MDS_URL"),
        PREFERENCE_SECURE_TRANSMISSION (TAG + ".PREFERENCE_SECURE_TRANSMISSION"),
        PHONE_NUMBER_KEY            ( TAG + ".PHONE_NUMBER_KEY");

        private String key;

        UserPrefKey(String key) {
            this.key = key;
        }

        public String get() {
            return key;
        }
    }

    private UserSettings(Context ctxt) {
        Context mApplicationContext = ctxt.getApplicationContext();
        //settings = mApplicationContext.getSharedPreferences(APP_PREFS_FILE, Context.MODE_PRIVATE);
        settings = PreferenceManager.getDefaultSharedPreferences(mApplicationContext);
    }

    public UserSettings() {
        this(PowerFailureApp.getInstance().getApplicationContext());
        isFirstTime();
    }

    public boolean isFirstTime() {
        if (settings.getBoolean(UserPrefKey.FIRST_TIME_KEY.get(), true)) {
            // record the fact that the app has been started at least once
            settings.edit().putBoolean(UserPrefKey.FIRST_TIME_KEY.get(), false).apply();
            setCredentials("guest", "Sanamobile1");
            return true;
        }
        return false;
    }

    private void remove(UserPrefKey key) {
        settings.edit().remove(key.get()).apply();
    }

    private void setUserPref(UserPrefKey key, String pref) {
        settings.edit().putString(key.get(), pref).apply();
    }

    private void setUserPref(UserPrefKey key, boolean pref) {
        settings.edit().putBoolean(key.get(), pref).apply();
    }

    private void setUserPref(UserPrefKey key, int pref) {
        settings.edit().putInt(key.get(), pref).apply();
    }

    private String getUserStringPref(UserPrefKey key) {
        return settings.getString(key.get(), "");
    }

    private int getUserIntPref(UserPrefKey key) {
        return settings.getInt(key.get(), -1);
    }

    private boolean getUserBoolPref(UserPrefKey key) {
        return settings.getBoolean(key.get(), false);
    }


    public void setCredentials (String userName, String password) {
        setUserPref(UserPrefKey.USERNAME_KEY, userName);
        setUserPref(UserPrefKey.PASSWORD_KEY, password);
    }

    public void clearCredentials() {
        remove(UserPrefKey.USERNAME_KEY);
        remove(UserPrefKey.PASSWORD_KEY);
    }


    public String getUsername () {
        return settings.contains(UserPrefKey.USERNAME_KEY.get()) ? getUserStringPref(UserPrefKey.USERNAME_KEY) : null;
    }

    public String getPassword () {
        return settings.contains(UserPrefKey.PASSWORD_KEY.get()) ? getUserStringPref(UserPrefKey.PASSWORD_KEY) : null;
    }



    public void setSecureTransmission() {
        setUserPref(UserPrefKey.PREFERENCE_SECURE_TRANSMISSION, true);
    }

    public String getPatientId() {
        return settings.contains(UserPrefKey.PATIENT_ID_KEY.get()) ? getUserStringPref(UserPrefKey.PATIENT_ID_KEY) : null;
    }

    public void setPatientId(String patientId) {
        setUserPref(UserPrefKey.PATIENT_ID_KEY, patientId);
    }

    public String getPhoneNumber() {
        return settings.contains(UserPrefKey.PHONE_NUMBER_KEY.get()) ? getUserStringPref(UserPrefKey.PHONE_NUMBER_KEY) : null;
    }
}
