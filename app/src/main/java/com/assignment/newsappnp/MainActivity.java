package com.assignment.newsappnp;

import android.os.Bundle;

import com.assignment.newsappnp.ui.main.PageViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.assignment.newsappnp.ui.main.SectionsPagerAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    PageViewModel pageViewModel = new PageViewModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ViewPager viewPager = findViewById(R.id.view_pager);
        final TabLayout tabs = findViewById(R.id.tabs);
        ArrayList<String> tabTitle = new ArrayList<>();
        final SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());

        pageViewModel.getNewsSources();

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                tabs.setupWithViewPager(viewPager);
                viewPager.setAdapter(sectionsPagerAdapter);
            }
        }, 1000);

//        for (int l = 0; l < 6; l++) {
//
//            tabs.addTab(tabs.newTab().setText("Pitch-" + l));
//            tabTitle.add("P - " + l);
//        }
//
//        SectionsPagerAdapter adapter = new SectionsPagerAdapter
//                (this, getSupportFragmentManager(), tabs.getTabCount(), tabTitle);
//        viewPager.setAdapter(adapter);
//        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabs));
    }
}