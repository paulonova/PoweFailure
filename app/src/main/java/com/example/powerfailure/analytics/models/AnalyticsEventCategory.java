package com.example.powerfailure.analytics.models;

/** * Created by Paulo Vila Nova on 2017-06-14.
 */

public enum AnalyticsEventCategory {

    USER_ACTION("user action"),
    RESULT("result"),
    NONE("unspecified");

    private String categoryText;

    AnalyticsEventCategory(String categoryText) {
        this.categoryText = categoryText;
    }

    @Override
    public String toString() {
        return categoryText;
    }
}
