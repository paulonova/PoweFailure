package com.example.powerfailure.analytics.models;

/** * Created by Paulo Vila Nova on 2017-06-14.
 */

public class AnalyticsScreenData {

    private String screenName;

    public AnalyticsScreenData(String rawScreenName) {
        ScreenName screenNameEnum = ScreenName.getScreenFromName(rawScreenName);
    }

    public String getScreenName() {
        return screenName;
    }
}
