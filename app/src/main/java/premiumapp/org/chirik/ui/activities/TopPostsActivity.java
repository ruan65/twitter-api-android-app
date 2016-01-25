package premiumapp.org.chirik.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.twitter.sdk.android.core.TwitterCore;

import java.util.List;

import butterknife.Bind;
import premiumapp.org.chirik.R;
import premiumapp.org.chirik.entities.Post;
import premiumapp.org.chirik.presenters.PostsListPresenter;
import premiumapp.org.chirik.presenters.PostsListPresenterImpl;
import premiumapp.org.chirik.presenters.PresenterHolder;
import premiumapp.org.chirik.ui.recycler_adapters.PostsRecyclerAdapter;
import premiumapp.org.chirik.ui.views.PostsListView;

public class TopPostsActivity extends InitActivity
        implements RecyclerView.OnItemTouchListener, PostsListView {

    @Bind(R.id.recyclerView) RecyclerView recyclerView;

    private PostsRecyclerAdapter adapter;
    private GestureDetectorCompat gestureDetector;
    private PostsListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addOnItemTouchListener(this);
        gestureDetector = new GestureDetectorCompat(this, new LocalOnGestureListener());

        presenter = createPresenter();
        presenter.init();
    }

    private PostsListPresenter createPresenter() {

        PostsListPresenter presenter = PresenterHolder.getInstance()
                .getPresenter(PostsListPresenter.class);

        if (presenter != null) {
            presenter.setView(this);
        } else {
            presenter = new PostsListPresenterImpl(this);
        }
        return presenter;
    }

    @NonNull
    @Override
    Integer getLayout() {
        return R.layout.activity_content_recycler;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        presenter.setView(null);
        PresenterHolder.getInstance().putPresenter(TopPostsActivity.class, presenter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isFinishing()) {
            PresenterHolder.getInstance().remove(TopPostsActivity.class);
        }
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

        gestureDetector.onTouchEvent(e);
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
    }

    @Override
    public void setPosts(List<Post> posts) {

        adapter = new PostsRecyclerAdapter(posts);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void logout() {

        TwitterCore.getInstance().logOut();
        finish();
        startActivity(new Intent(this, LoginActivity.class));
    }

    private class LocalOnGestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            View view = recyclerView.findChildViewUnder(e.getX(), e.getY());
            onClick(view);
            return super.onSingleTapConfirmed(e);
        }

        @Override
        public void onLongPress(MotionEvent e) {
        }
    }

    private void onClick(View view) {

        int pos = recyclerView.getChildLayoutPosition(view);
        Post data = adapter.getItem(pos);

        View innerContainer = view.findViewById(R.id.container_inner_item);

        Toast.makeText(this, "kinda starting activity", Toast.LENGTH_LONG).show();
    }
}
