package com.assignment.newsappnp.Adapters;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import com.assignment.newsappnp.common.Strings;
import com.assignment.newsappnp.ui.main.PageViewModel;
import com.assignment.newsappnp.Fragments.TabsPageFragment;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class TabsPagerAdapter extends FragmentPagerAdapter {
//    @StringRes
    private final Context mContext;

    public TabsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a TabsPageFragment (defined as a static inner class below).
        return TabsPageFragment.newInstance(position + 1);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return PageViewModel.TAB_TITLES[position];
    }

    @Override
    public int getCount() {
        // Show 10 total pages.
        return Strings.mNumOfTabs;
    }
}