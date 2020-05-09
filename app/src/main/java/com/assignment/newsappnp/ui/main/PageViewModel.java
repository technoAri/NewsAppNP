package com.assignment.newsappnp.ui.main;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PageViewModel extends ViewModel {

    String country = "in";
    String apiKey = "4a1eabbbdffb429ebbcd12771e5d684c";
    private MutableLiveData<List<NewsModel>> newsList;
//    private List<String> newsList = new ArrayList<String>();
    private MutableLiveData<Integer> mIndex = new MutableLiveData<>();
    private LiveData<String> mText = Transformations.map(mIndex, new Function<Integer, String>() {
        @Override
        public String apply(Integer input) {
            return "Hello world from section: " + input;
        }
    });

    public LiveData<List<NewsModel>> getNewsSources() {
        //if the list is null
        if (newsList == null) {
            newsList = new MutableLiveData<List<NewsModel>>();
            //we will load it asynchronously from server in this method
            loadNews();
        }

        //finally we will return the list
        return newsList;
    }

    private void loadNews() {
        Retrofit retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl(NewsApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        NewsApi newsApi = retrofit.create(NewsApi.class);
        Call<NewsList> call = newsApi.getNewsSources(country, apiKey);


        call.enqueue(new Callback<NewsList>() {
            @Override
            public void onResponse(Call<NewsList> call, Response<NewsList> response) {

                //finally we are setting the list to our MutableLiveData
                generateNoticeList((ArrayList<NewsModel>) response.body().getNewsArray());
                System.out.println("NewsTitle"+response.body().getNewsArray());
            }

            @Override
            public void onFailure(Call<NewsList> call, Throwable t) {
                System.out.println(call);
            }
        });
    }

    private void generateNoticeList(ArrayList<NewsModel> newsArrayList) {
        System.out.println("NewsTitle11"+newsArrayList.get(0).getSource().name);

//        private LiveData<String> mText = Transformations.map(mIndex, new Function<Integer, String>() {
//            @Override
//            public String apply(Integer input) {
//                return "Hello world from section: " + input;
//            }
//        });
//        recyclerView = findViewById(R.id.recycler_view_notice_list);
//        adapter = new NoticeAdapter(noticeArrayList);
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setAdapter(adapter);

//        for (int l = 0; l < 6; l++) {
//
//            tabs.addTab(tabs.newTab().setText("Pitch-" + l));
//            tabTitle.add("P - " + l);
//        }
//
//        SectionsPagerAdapter adapter = new SectionsPagerAdapter
//                (getSupportFragmentManager(), tabs.getTabCount(), tabTitle);
//        viewPager.setAdapter(adapter);
//        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab));

    }

    public void setIndex(int index) {
        mIndex.setValue(index);
    }

    public LiveData<String> getText() {
        return mText;
    }
}