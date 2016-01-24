package premiumapp.org.chirik.ui;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;

public interface ScreenContainer {

    ViewGroup bind(AppCompatActivity activity);

    DrawerLayout getDrawerLayout();
}
