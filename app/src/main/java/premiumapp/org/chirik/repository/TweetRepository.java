package premiumapp.org.chirik.repository;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.models.Tweet;

import java.util.List;

public interface TweetRepository {
    void getTimeLine(final Callback<List<Tweet>> callback);
}
