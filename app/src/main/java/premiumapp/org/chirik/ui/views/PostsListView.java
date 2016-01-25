package premiumapp.org.chirik.ui.views;

import java.util.List;

import premiumapp.org.chirik.entities.Post;

public interface PostsListView {

    void setPosts(List<Post> posts);

    void logout();
}
