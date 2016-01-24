package premiumapp.org.chirik.ui;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import premiumapp.org.chirik.R;
import premiumapp.org.chirik.ui.activities.TopPicturesActivity;
import premiumapp.org.chirik.ui.activities.TopPostsActivity;

public class ScreenContainerImpl implements ScreenContainer  {

    @Bind(R.id.drawer_layout) DrawerLayout drawerLayout;
    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.navigation_view) NavigationView navigationView;
    @Bind(R.id.activity_content) ViewGroup container;

    private TextView usernameTv;
    private ImageView userProfileIv;

    @Override
    public ViewGroup bind(AppCompatActivity activity) {

        activity.setContentView(R.layout.activity_base);
        ButterKnife.bind(this, activity);
        setUpDrawerLayout(activity);
        initToolbar(activity);

        return container;
    }

    @Override
    public DrawerLayout getDrawerLayout() {
        return drawerLayout;
    }

    private void setUpDrawerLayout(AppCompatActivity activity) {

        usernameTv = (TextView) navigationView.findViewById(R.id.username);
        userProfileIv = (ImageView) navigationView.findViewById(R.id.avatar);

        if (activity instanceof TopPostsActivity) {
            navigationView.getMenu().getItem(0).setChecked(true);
        } else if (activity instanceof TopPicturesActivity) {
            navigationView.getMenu().getItem(1).setChecked(true);
        }

        navigationView.setNavigationItemSelectedListener(item -> {

            Intent intent = null;
            item.setChecked(true);

            switch (item.getItemId()) {
                case R.id.drawer_top_articles:
                    if (!(activity instanceof TopPostsActivity)) {
                        intent = new Intent(activity, TopPostsActivity.class);
                    }
                    break;
                case R.id.drawer_top_images:
                    if (!(activity instanceof TopPicturesActivity)) {
                        intent = new Intent(activity, TopPicturesActivity.class);
                    }
                    break;
            }

            if (intent != null) {
                activity.startActivity(intent);
                activity.finish();
            }
            drawerLayout.closeDrawers();
            return true;
        });
    }

    private void initToolbar(AppCompatActivity activity) {

        activity.setSupportActionBar(toolbar);
        final ActionBar actionBar = activity.getSupportActionBar();

        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
}
