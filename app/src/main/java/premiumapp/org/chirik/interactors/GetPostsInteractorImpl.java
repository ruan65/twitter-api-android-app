package premiumapp.org.chirik.interactors;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.core.models.UrlEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import premiumapp.org.chirik.entities.Post;
import premiumapp.org.chirik.repository.TweetRepository;
import premiumapp.org.chirik.repository.TweetRepositoryImpl;

public class GetPostsInteractorImpl implements GetTopPostsInteractor {

    private final TweetRepository tweetRepository;

    public GetPostsInteractorImpl() {
        this(new TweetRepositoryImpl(TwitterCore.getInstance().getSessionManager()
                .getActiveSession()));
    }

    public GetPostsInteractorImpl(TweetRepository tweetRepository) {
        this.tweetRepository = tweetRepository;
    }

    @Override
    public void execute(Callback<List<Post>> callback) {

        tweetRepository.getTimeLine(new Callback<List<Tweet>>() {
            @Override
            public void success(Result<List<Tweet>> result) {

                List<Post> items = processTweets(result);
                callback.success(items, null);
            }

            @Override
            public void failure(TwitterException e) {

                callback.failure(e);
            }
        });
    }

    private List<Post> processTweets(Result<List<Tweet>> result) {

        final List<Post> items = new ArrayList<>();

        for (Tweet tweet : result.data) {
            if (tweet.entities != null && tweet.entities.urls != null
                    && tweet.entities.urls.size() > 0 && isEligibleDomain(tweet.entities.urls)) {

                items.add(Post.create(tweet));
            }
        }
        Collections.sort(items);
        return items;
    }

    private boolean isEligibleDomain(List<UrlEntity> urls) {
        if (urls == null || urls.get(0) == null || urls.get(0).expandedUrl == null) {
            return false;
        }
        final String url = urls.get(0).expandedUrl;
        return !url.contains("twitter.com") && !url.contains("goo.gl") &&
                !url.contains("vine.co") && !url.contains("vimeo.com") &&
                !url.contains("youtube.com") && !url.contains("youtu.be");
    }
}
