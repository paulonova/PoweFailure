package com.example.powerfailure.analytics.providers;

import com.crashlytics.android.answers.Answers;
import com.crashlytics.android.answers.ContentViewEvent;
import com.crashlytics.android.answers.CustomEvent;
import com.crashlytics.android.answers.LevelStartEvent;
import com.example.powerfailure.analytics.models.AnalyticsEventInfo;
import com.example.powerfailure.analytics.models.AnalyticsEventLabel;
import com.example.powerfailure.analytics.models.AnalyticsScreenData;

/** * Created by Paulo Vila Nova on 2017-06-14.
 */

public class TwitterAnswersProvider extends AppAnalyticsProvider {

    public static final AppAnalyticsProvider.Factory FACTORY = new AppAnalyticsProvider.Factory() {
        @Override
        public AppAnalyticsProvider create() {
            return new TwitterAnswersProvider();
        }
    };

    private TwitterAnswersProvider() {}
    @Override
    public void trackEvent(AnalyticsEventInfo eventInfo) {
        if (eventInfo.hasTypeLabel() && eventInfo.getLabel().equals(AnalyticsEventLabel.BATTERY_INFO)) {
            Answers.getInstance().logLevelStart(new LevelStartEvent()
                    .putLevelName(eventInfo.getStringValue()));
        } else {
            Answers.getInstance().logCustom(new CustomEvent(eventInfo.getAnalyticsTypeEvent().toString())
                    .putCustomAttribute("Label", eventInfo.getLabel())
                    .putCustomAttribute("Category", eventInfo.getAnalyticsEventCategory().toString()));
        }
    }

    @Override
    public void trackScreen(AnalyticsScreenData screenData) {
        Answers.getInstance().logContentView(new ContentViewEvent()
                .putContentName(screenData.getScreenName())
                .putContentType("screen group1")
                .putContentId("screen Id1"));
    }
}
