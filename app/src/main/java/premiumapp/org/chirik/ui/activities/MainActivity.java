package premiumapp.org.chirik.ui.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;

import io.fabric.sdk.android.Fabric;

public class MainActivity extends AppCompatActivity {

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
