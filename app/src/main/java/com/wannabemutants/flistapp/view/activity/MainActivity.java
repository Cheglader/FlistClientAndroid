package com.wannabemutants.flistapp.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.wannabemutants.flistapp.R;
import com.wannabemutants.flistapp.view.fragment.CategoriesFragment;
import com.wannabemutants.flistapp.view.fragment.RestaurantsFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addCategoriesFragment();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_view_on_github:
                //TODO: Add github link
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void addCategoriesFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_frame, new CategoriesFragment())
                .commit();
    }

    private void addRestaurantsFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_frame, new RestaurantsFragment())
                .commit();
    }
}
