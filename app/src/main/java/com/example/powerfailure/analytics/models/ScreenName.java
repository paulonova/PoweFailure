package com.example.powerfailure.analytics.models;

import com.example.powerfailure.ui.MainActivity;
import com.example.powerfailure.ui.SettingsActivity;
import com.example.powerfailure.ui.SplashActivity;

/** * Created by Paulo Vila Nova on 2017-06-14.
 */

public enum ScreenName {

    SPLASH(SplashActivity.class.getSimpleName()),
    SETTINGS(SettingsActivity.class.getSimpleName()),
    MAIN(MainActivity.class.getSimpleName());

    private String screenName;

    ScreenName(String screenName) {
        this.screenName = screenName;
    }

    public String getName() {
        return screenName;
    }

    public static ScreenName getScreenFromName(String rawScreenName){

        for(ScreenName nameEnum : ScreenName.values()){
            if(nameEnum.getName().equals(rawScreenName)){
                return nameEnum;
            }
        }
        throw new IllegalStateException("Invalid screen name: " + rawScreenName);
    }


}
