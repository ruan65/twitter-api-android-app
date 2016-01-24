package premiumapp.org.chirik;

import android.app.Application;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;

import io.fabric.sdk.android.Fabric;
import timber.log.Timber;

public class ThisApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        setUpFabric();

        // Logging set up
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree() {
                @Override
                protected String createStackElementTag(StackTraceElement element) {
                    return super.createStackElementTag(element) +
                            ":timber: line=" + element.getLineNumber() +
                            " method: " + element.getMethodName();
                }
            });
        }
    }

    private void setUpFabric() {

        TwitterAuthConfig authConfig =
                new TwitterAuthConfig(BuildConfig.TWITTER_KEY, BuildConfig.TWITTER_SECRET);

        Fabric.with(this, new Twitter(authConfig));
    }
}
