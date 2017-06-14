package com.example.powerfailure.analytics.models;

/** * Created by Paulo Vila Nova on 2017-06-14.
 */

public enum AnalyticsEventLabel {

    BATTERY_INFO("BATTERY INFO");

    private String infotext;

    AnalyticsEventLabel(String infotext) {
        this.infotext = infotext;
    }

    @Override
    public String toString() {
        return infotext;
    }


}
