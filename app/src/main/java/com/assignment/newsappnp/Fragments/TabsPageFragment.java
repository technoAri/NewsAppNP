package com.assignment.newsappnp.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.assignment.newsappnp.R;
import com.assignment.newsappnp.Activities.NewsDetailsActivity;
import com.assignment.newsappnp.ui.main.PageViewModel;
import com.squareup.picasso.Picasso;

/**
 * A placeholder fragment containing a simple view.
 */
public class TabsPageFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;

    public static TabsPageFragment newInstance(int index) {
        TabsPageFragment fragment = new TabsPageFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        final TextView newsTitle = root.findViewById(R.id.tv_title);
        final TextView newsDesc = root.findViewById(R.id.tv_description);
        final TextView timestamp = root.findViewById(R.id.tv_timestamp);
        final TextView readMore = root.findViewById(R.id.tv_read_more);
        final TextView author = root.findViewById(R.id.tv_author);
        final ImageView thumbnail = root.findViewById(R.id.iv_thumbnail);

        pageViewModel.getTitleText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                newsTitle.setText(s);
            }
        });

        pageViewModel.getDescription().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                newsDesc.setText(s);
            }
        });

        pageViewModel.getTimeStamp().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                timestamp.setText(s);
            }
        });

        pageViewModel.getAuthor().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                author.setText(s + " | ");
            }
        });

        pageViewModel.getThumbnailUrl().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                Picasso.get().load(s).into(thumbnail);
            }
        });

        pageViewModel.getUrlToNews().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable final String s) {
                readMore.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), NewsDetailsActivity.class);
                        intent.putExtra("URL", s);
                        startActivity(intent);
                    }
                });
            }
        });
        return root;
    }
}