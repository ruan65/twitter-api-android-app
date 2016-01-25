package premiumapp.org.chirik.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.twitter.sdk.android.core.models.Tweet;

public class Post implements Comparable<Post>, Parcelable {

    private final String title;
    private final String url;
    private final int retweetCount;
    private final String mediaUrl;

    public Post(String title, String url, int retweetCount, String mediaUrl) {
        this.title = title;
        this.url = url;
        this.retweetCount = retweetCount;
        this.mediaUrl = mediaUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getRetweetCount() {
        return String.valueOf(retweetCount);
    }

    public String getMediaUrl() {
        return mediaUrl;
    }

    @Override
    public int compareTo(Post another) {

        if (equals(another) || retweetCount == another.retweetCount) return 0;
        return retweetCount > another.retweetCount ? -1 : 1;
    }

    public static Post create(Tweet tweet) {

        String imgUrl = null;

        if (tweet.entities.media != null && tweet.entities.media.size() > 0) {
            imgUrl = tweet.entities.media.get(0).mediaUrl;
        }

        final String title = tweet.text.split("http")[0];

        return new Post(title, tweet.entities.urls.get(0).expandedUrl, tweet.retweetCount,
                imgUrl == null ? "" : imgUrl + ":thumb");
    }

    /**
     * Parcelable implementation boilerplate (auto generated)
     */

    protected Post(Parcel in) {
        title = in.readString();
        url = in.readString();
        retweetCount = in.readInt();
        mediaUrl = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(url);
        dest.writeInt(retweetCount);
        dest.writeString(mediaUrl);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Post> CREATOR = new Creator<Post>() {
        @Override
        public Post createFromParcel(Parcel in) {
            return new Post(in);
        }

        @Override
        public Post[] newArray(int size) {
            return new Post[size];
        }
    };
}
