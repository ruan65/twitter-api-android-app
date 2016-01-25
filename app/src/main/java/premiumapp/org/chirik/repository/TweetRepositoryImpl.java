package premiumapp.org.chirik.repository;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.models.Tweet;

import java.util.List;

import premiumapp.org.chirik.services.CustomApiClient;
import timber.log.Timber;

public class TweetRepositoryImpl implements TweetRepository {

    private final CustomApiClient client;

    public TweetRepositoryImpl(TwitterSession session) {
        this.client = new CustomApiClient(session);
    }

    TweetRepositoryImpl(CustomApiClient client) {
        this.client = client;
    }

    @Override
    public void getTimeLine(Callback<List<Tweet>> callback) {

        client.getTimeLineService().homeTimeLine(200, true, true, true, true,
                new Callback<List<Tweet>>() {
                    @Override
                    public void success(Result<List<Tweet>> result) {
                        callback.success(result);
                    }

                    @Override
                    public void failure(TwitterException e) {
                        String message = String.format("api error: %s", e.getMessage());
                        Timber.d(message);
                        callback.failure(e);
                    }
                });
    }
}
