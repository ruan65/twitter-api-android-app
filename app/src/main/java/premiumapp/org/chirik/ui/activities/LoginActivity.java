package premiumapp.org.chirik.ui.activities;

import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RelativeLayout;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

import butterknife.Bind;
import butterknife.ButterKnife;
import premiumapp.org.chirik.R;
import premiumapp.org.chirik.utils.Cv;

public class LoginActivity extends AppCompatActivity {

    @Bind(R.id.login_container) RelativeLayout loginContainer;
    @Bind(R.id.twitter_login_button) TwitterLoginButton loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        loginButton.setCallback(new Callback<TwitterSession>() {

            @Override
            public void success(Result<TwitterSession> result) {

                String userName = result.data.getUserName();
                long userId = result.data.getUserId();

                // Store user's data into prefs
                PreferenceManager.getDefaultSharedPreferences(LoginActivity.this).edit()
                        .putString(Cv.KEY_USERNAME, userName).putLong(Cv.KEY_ID, userId).apply();

                startApp();
            }

            @Override
            public void failure(TwitterException e) {

                Snackbar.make(loginContainer, R.string.try_again, Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    private void startApp() {
        finish();

        startActivity(new Intent(this, TopPostsActivity.class));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        loginButton.onActivityResult(requestCode, resultCode, data);
    }
}
