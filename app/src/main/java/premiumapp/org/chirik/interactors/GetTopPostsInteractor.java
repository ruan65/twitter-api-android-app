package premiumapp.org.chirik.interactors;

import com.twitter.sdk.android.core.Callback;

import java.util.List;

import premiumapp.org.chirik.entities.Post;

public interface GetTopPostsInteractor {
    void execute(Callback<List<Post>> posts);
}
