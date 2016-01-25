package premiumapp.org.chirik.presenters;

import premiumapp.org.chirik.ui.views.PostsListView;

public interface PostsListPresenter extends Presenter {

    void init();

    void setView(PostsListView view);
}
