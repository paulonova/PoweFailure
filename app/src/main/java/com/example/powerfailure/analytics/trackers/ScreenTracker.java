package com.example.powerfailure.analytics.trackers;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.example.powerfailure.PowerFailureApp;
import com.example.powerfailure.analytics.models.AnalyticsScreenData;
import com.example.powerfailure.analytics.providers.AppAnalyticsProvider;

/** * Created by Paulo Vila Nova on 2017-06-15.
 */

public class ScreenTracker implements Application.ActivityLifecycleCallbacks {


    private AppAnalyticsProvider provider;

    public ScreenTracker(AppAnalyticsProvider provider) {
        this.provider = provider;
        PowerFailureApp.getInstance().registerActivityLifecycleCallbacks(this);
    }


    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

        provider.trackScreen(new AnalyticsScreenData(activity.getClass().getSimpleName()));

    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }
}
