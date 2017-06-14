package com.example.powerfailure.analytics.models;

/** * Created by Paulo Vila Nova on 2017-06-14.
 */

public enum AnalyticsTypeEvent {

    BATTERY_PERCENT("Battery Percentage"),
    POWER_FAILED("Power disconnected event"),
    POWER_CONNECTED("Power connected event");

    private String eventTest;

    AnalyticsTypeEvent(String eventTest) {
        this.eventTest = eventTest;
    }


    @Override
    public String toString() {
        return eventTest;
    }
}
