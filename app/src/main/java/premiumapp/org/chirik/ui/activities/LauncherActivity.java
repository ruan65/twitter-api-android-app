package premiumapp.org.chirik.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.twitter.sdk.android.core.TwitterCore;

public class LauncherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (TwitterCore.getInstance().getSessionManager().getActiveSession() == null) {

            startLogin();
        } else {

            startApp();
        }

    }

    private void startApp() {

        finish();

        startActivity(new Intent(this, TopPostsActivity.class));
    }

    private void startLogin() {

        finish();

        startActivity(new Intent(this, LoginActivity.class));
    }
}
