package com.example.powerfailure.analytics.trackers;

import com.example.powerfailure.analytics.models.AnalyticsEventCategory;
import com.example.powerfailure.analytics.models.AnalyticsEventInfo;
import com.example.powerfailure.analytics.models.AnalyticsEventLabel;
import com.example.powerfailure.analytics.models.AnalyticsTypeEvent;
import com.example.powerfailure.analytics.providers.AppAnalyticsProvider;
import com.example.powerfailure.models.BatteryStatus;

/** * Created by Paulo Vila Nova on 2017-06-15.
 */

public class BatteryTracker {

    private AppAnalyticsProvider provider;

    public BatteryTracker(AppAnalyticsProvider provider) {
        this.provider = provider;
    }



    public void powerStatus(BatteryStatus status){
        AnalyticsTypeEvent event = (status.isCharging())
                ? AnalyticsTypeEvent.POWER_CONNECTED : AnalyticsTypeEvent.POWER_FAILED;
        provider.trackEvent(new AnalyticsEventInfo(AnalyticsEventCategory.RESULT, event, "power status"));
    }

    public void batteryStatus(BatteryStatus status){
        AnalyticsTypeEvent event = AnalyticsTypeEvent.BATTERY_PERCENT;
        String value = String.valueOf(status.getBatteryPercent());

        provider.trackEvent(new AnalyticsEventInfo(AnalyticsEventCategory.RESULT,
                event, AnalyticsEventLabel.BATTERY_INFO, value));
    }



}
