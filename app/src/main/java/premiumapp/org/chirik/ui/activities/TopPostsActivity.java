package premiumapp.org.chirik.ui.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;

import premiumapp.org.chirik.R;

public class TopPostsActivity extends InitActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @NonNull
    @Override
    Integer getLayout() {
        return R.layout.activity_content_recycler;
    }
}
