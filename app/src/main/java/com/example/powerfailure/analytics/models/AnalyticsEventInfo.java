package com.example.powerfailure.analytics.models;

/** * Created by Paulo Vila Nova on 2017-06-14.
 */

public class AnalyticsEventInfo {

    private AnalyticsEventCategory analyticsEventCategory;
    private AnalyticsEventLabel analyticsEventLable;
    private AnalyticsTypeEvent analyticsTypeEvent;
    private String stringValue;
    private String lable;
    private Integer value;

    public AnalyticsEventInfo(AnalyticsEventCategory analyticsEventCategory, AnalyticsTypeEvent analyticsTypeEvent) {
        this.analyticsEventCategory = analyticsEventCategory;
        this.analyticsTypeEvent = analyticsTypeEvent;
    }



    public String getStringValue() {
        return stringValue;
    }

    public String getLable() {
        return lable;
    }

    public Integer getValue() {
        return value;
    }


    public AnalyticsEventCategory getAnalyticsEventCategory() {
        return analyticsEventCategory;
    }

    public AnalyticsEventLabel getAnalyticsEventLable() {
        return analyticsEventLable;
    }

    public AnalyticsTypeEvent getAnalyticsTypeEvent() {
        return analyticsTypeEvent;
    }


    public boolean hasStringLabel(){
        return lable != null;
    }

    public boolean hasTypeLabel(){
        return analyticsEventLable != null;
    }

    public boolean hasValue(){
        return value != null;
    }



}
