package com.example.powerfailure.analytics.providers;

import com.example.powerfailure.analytics.models.AnalyticsEventInfo;
import com.example.powerfailure.analytics.models.AnalyticsScreenData;

/** * Created by Paulo Vila Nova on 2017-06-14.
 */

public interface AppAnalyticsTracker {

    public void trackEvent(AnalyticsEventInfo eventInfo);

    public void trackScreen(AnalyticsScreenData screenData);


    /** More methods can be created if necessary*/
//    public void trackGAmeLevel();
//    public void trackEcommerceTransaction();


}
