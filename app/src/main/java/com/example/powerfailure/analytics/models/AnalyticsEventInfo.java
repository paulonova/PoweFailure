package com.example.powerfailure.analytics.models;

/** * Created by Paulo Vila Nova on 2017-06-14.
 */

public class AnalyticsEventInfo {
    private AnalyticsEventCategory analyticsEventCategory;
    private AnalyticsEventLabel analyticsEventLabel;
    private AnalyticsTypeEvent analyticsTypeEvent;
    private String stringValue;
    private String label;
    private Integer value;

    public AnalyticsEventInfo(AnalyticsEventCategory analyticsEventCategory, AnalyticsTypeEvent analyticsTypeEvent) {
        this.analyticsEventCategory = analyticsEventCategory;
        this.analyticsTypeEvent = analyticsTypeEvent;
    }

    public AnalyticsEventInfo(AnalyticsEventCategory result, AnalyticsTypeEvent event, String extra) {
        this(result, event);
        this.label = extra != null ? label : null;
    }

    public AnalyticsEventInfo(AnalyticsEventCategory result, AnalyticsTypeEvent event, AnalyticsEventLabel batteryInfo, String value) {
        this(result, event, value);
        this.analyticsEventLabel = batteryInfo;
        this.label = analyticsEventLabel.toString();
    }

    public String getStringValue() {
        return stringValue;
    }

    public String getLabel() {
        return label;
    }

    public Integer getValue() {
        return value;
    }

    public AnalyticsEventCategory getAnalyticsEventCategory() {
        return analyticsEventCategory;
    }

    public AnalyticsEventLabel getAnalyticsEventLabel() {
        return analyticsEventLabel;
    }

    public AnalyticsTypeEvent getAnalyticsTypeEvent() {
        return analyticsTypeEvent;
    }

    public boolean hasStringLabel() {
        return label != null;
    }

    public boolean hasTypeLabel() {
        return analyticsEventLabel != null;
    }

    public boolean hasValue() {
        return value != null;
    }
}
