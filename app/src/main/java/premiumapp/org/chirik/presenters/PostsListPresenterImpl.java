package premiumapp.org.chirik.presenters;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;

import java.util.ArrayList;
import java.util.List;

import premiumapp.org.chirik.entities.Post;
import premiumapp.org.chirik.interactors.GetPostsInteractorImpl;
import premiumapp.org.chirik.interactors.GetTopPostsInteractor;
import premiumapp.org.chirik.ui.views.PostsListView;

public class PostsListPresenterImpl implements PostsListPresenter {

    private PostsListView view;
    private final GetTopPostsInteractor useCaseInteractor;
    private List<Post> posts;

    public PostsListPresenterImpl(PostsListView view, GetTopPostsInteractor useCaseInteractor) {
        this.useCaseInteractor = useCaseInteractor;
        this.view = view;
    }

    public PostsListPresenterImpl(PostsListView view) {
        this(view, new GetPostsInteractorImpl());
    }

    @Override
    public void init() {

        if (posts == null || posts.size() == 0) {
            posts = new ArrayList<>();
            useCaseInteractor.execute(new Callback<List<Post>>() {

                @Override
                public void success(Result<List<Post>> result) {
                    posts.addAll(result.data);
                    setPosts(posts);
                }

                @Override
                public void failure(TwitterException e) {

                    if (view != null) {
                        view.logout();
                    }
                }
            });
        }
    }

    private void setPosts(List<Post> posts) {
        if (view != null) {
            view.setPosts(posts);
        }
    }

    @Override
    public void setView(PostsListView view) {

        this.view = view;
    }
}
