package com.example.powerfailure.analytics;

import com.example.powerfailure.analytics.providers.AppAnalyticsProvider;
import com.example.powerfailure.analytics.providers.TwitterAnswersProvider;
import com.example.powerfailure.analytics.trackers.BatteryTracker;
import com.example.powerfailure.analytics.trackers.ScreenTracker;

/** * Created by Paulo Vila Nova on 2017-06-15.
 */

public class AppAnalytics {

    private static AppAnalyticsProvider provider;
    private static AppAnalytics singleton;


    public static AppAnalytics getInstance(){
        if(singleton == null){
            singleton = new AppAnalytics();
        }
        return singleton;
    }

    public AppAnalytics() {
    }

    //Avoid Thread problems..
    private static class InstanceHolder{
        static ScreenTracker SCREEN_TRACKER_INSTANCE = new ScreenTracker(getProvider());
        static BatteryTracker BATTERY_TRACKER_INSTANCE = new BatteryTracker(getProvider());
    }

    private static AppAnalyticsProvider getProvider(){
        if(provider == null){
            provider = TwitterAnswersProvider.FACTORY.create();
        }
        return provider;
    }

    public BatteryTracker batteryTracker(){
        return InstanceHolder.BATTERY_TRACKER_INSTANCE;
    }

    public ScreenTracker screenTracker(){
        return InstanceHolder.SCREEN_TRACKER_INSTANCE;
    }


}
