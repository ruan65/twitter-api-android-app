package premiumapp.org.chirik.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;

import com.twitter.sdk.android.core.TwitterCore;

import butterknife.ButterKnife;
import premiumapp.org.chirik.R;
import premiumapp.org.chirik.ui.ScreenContainer;
import premiumapp.org.chirik.ui.ScreenContainerImpl;
import timber.log.Timber;

public abstract class InitActivity extends AppCompatActivity {

    private ViewGroup mainFrame;
    private ScreenContainer screenContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        screenContainer = new ScreenContainerImpl();

        mainFrame = screenContainer.bind(this);

        getLayoutInflater().inflate(getLayout(), mainFrame);

        ButterKnife.bind(this);
    }

    @NonNull
    abstract Integer getLayout();

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.general, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                screenContainer.getDrawerLayout().openDrawer(GravityCompat.START);
                return true;

            case R.id.action_logout:
                logout();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        TwitterCore.getInstance().logOut();
        finish();
        startActivity(new Intent(this, LoginActivity.class));
    }
}
