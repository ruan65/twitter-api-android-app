package premiumapp.org.chirik.ui.recycler_adapters;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import premiumapp.org.chirik.R;
import premiumapp.org.chirik.entities.Post;

public class PostsRecyclerAdapter extends RecyclerView.Adapter<PostsRecyclerAdapter.PostsViewHolder> {

    private List<Post> items;

    public PostsRecyclerAdapter(List<Post> items) {
        if (items == null) {
            throw new NullPointerException("items must not be null");
        }
        this.items = items;
    }

    @Override
    public PostsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.post_layout, parent, false);

        return new PostsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PostsViewHolder holder, int position) {

        final Post post = items.get(position);
        holder.title.setText(post.getTitle());
        holder.retweet.setText(post.getRetweetCount());

        if (!TextUtils.isEmpty(post.getMediaUrl())) {
            holder.media.setVisibility(View.VISIBLE);
            Picasso.with(holder.media.getContext())
                    .load(post.getMediaUrl())
                    .into(holder.media);
        } else {
            holder.media.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public Post getItem(int position) {
        return items.get(position);
    }

    final static class PostsViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.title) TextView title;
        @Bind(R.id.retweet) TextView retweet;
        @Bind(R.id.media) ImageView media;

        public PostsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
