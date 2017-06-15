package com.example.powerfailure.analytics.providers;

/** * Created by Paulo Vila Nova on 2017-06-14.
 */

public abstract class AppAnalyticsProvider implements AppAnalyticsTracker {

    public interface Factory{
        public AppAnalyticsProvider create();
    }


}
