package premiumapp.org.chirik.services;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.models.User;

public class CustomApiClient extends TwitterApiClient {

    private final TwitterSession session;

    public CustomApiClient(TwitterSession session) {
        super(session);
        this.session = session;
    }

    public TimeLineService getTimeLineService() {
        return getService(TimeLineService.class);
    }

    public UserService getUserService() {
        return getService(UserService.class);
    }

    public void userShow(Callback<User> callback) {
        getUserService().userShow(session.getUserName(), true, callback);
    }
}
