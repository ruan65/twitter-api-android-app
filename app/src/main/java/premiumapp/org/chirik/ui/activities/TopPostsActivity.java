package premiumapp.org.chirik.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import premiumapp.org.chirik.R;
import timber.log.Timber;

public class TopPostsActivity extends AppCompatActivity {

    @Bind(R.id.tv_user) TextView mTvUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Timber.i("creating");
        setContentView(R.layout.activity_top_posts);
        ButterKnife.bind(this);
//        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
//        mTvUser.setText(String.format("User name: %s\nUser id: %d",
//                prefs.getString(Cv.KEY_USERNAME, ""),
//                prefs.getLong(Cv.KEY_ID, -1)));
    }
}
