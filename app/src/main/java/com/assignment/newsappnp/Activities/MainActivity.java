package com.assignment.newsappnp.Activities;

import android.app.ProgressDialog;
import android.os.Bundle;

import com.assignment.newsappnp.R;
import com.assignment.newsappnp.ui.main.PageViewModel;
import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Handler;
import com.assignment.newsappnp.Adapters.TabsPagerAdapter;

public class MainActivity extends AppCompatActivity {

    // View Model
    PageViewModel pageViewModel = new PageViewModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ViewPager viewPager = findViewById(R.id.view_pager);
        final TabLayout tabs = findViewById(R.id.tabs);
        final TabsPagerAdapter tabsPagerAdapter = new TabsPagerAdapter(this, getSupportFragmentManager());

        pageViewModel.getNewsSources();

        final ProgressDialog dialog = new ProgressDialog(MainActivity.this);
        dialog.setMessage("Please Wait...");
        dialog.show();
        final Handler handler = new Handler();
        // applying the delay as the title of the tabs are getting set from API response
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                tabs.setupWithViewPager(viewPager);
                viewPager.setAdapter(tabsPagerAdapter);
                dialog.dismiss();
            }
        }, 1500);
    }
}