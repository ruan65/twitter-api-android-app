package premiumapp.org.chirik;

import android.app.Application;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;

import io.fabric.sdk.android.Fabric;

public class ThisApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        setUpFabric();
    }

    private void setUpFabric() {

        TwitterAuthConfig authConfig =
                new TwitterAuthConfig(BuildConfig.TWITTER_KEY, BuildConfig.TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
    }
}
