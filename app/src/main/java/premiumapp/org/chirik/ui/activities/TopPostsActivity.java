package premiumapp.org.chirik.ui.activities;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import premiumapp.org.chirik.R;
import premiumapp.org.chirik.utils.Cv;

public class TopPostsActivity extends AppCompatActivity {

    @Bind(R.id.tv_user) TextView mTvUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_posts);
        ButterKnife.bind(this);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        mTvUser.setText(String.format("User name: %s\nUser id: %d",
                prefs.getString(Cv.KEY_USERNAME, ""),
                prefs.getLong(Cv.KEY_ID, -1)));
    }
}
