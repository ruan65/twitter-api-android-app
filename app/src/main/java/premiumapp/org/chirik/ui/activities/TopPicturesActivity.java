package premiumapp.org.chirik.ui.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Menu;

import premiumapp.org.chirik.R;
import timber.log.Timber;

public class TopPicturesActivity extends InitActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Timber.i("creating");

    }

    @NonNull
    @Override
    Integer getLayout() {
        return R.layout.activity_recycler;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_article_list, menu);
        return true;
    }
}
