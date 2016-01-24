package premiumapp.org.chirik.ui.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import premiumapp.org.chirik.ui.ScreenContainer;
import premiumapp.org.chirik.ui.ScreenContainerImpl;

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
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                screenContainer.getDrawerLayout().openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
