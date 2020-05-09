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
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(NewsApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        NewsApi newsApi = retrofit.create(NewsApi.class);
        Call<NewsList> call = newsApi.getNewsSources(country, apiKey);


        call.enqueue(new Callback<NewsList>() {
            @Override
            public void onResponse(Call<NewsList> call, Response<NewsList> response) {

                //finally we are setting the list to our MutableLiveData
//                newsList.setValue(response.body().get);
//                news = response.body().getNewsArray();
                System.out.println("NewsTitle"+response.body().getNewsArray());
                NewsModel newsModel = new NewsModel();
                System.out.println("NewsTitle" + newsModel.getNewsTitle());
            }

            @Override
            public void onFailure(Call<NewsList> call, Throwable t) {
                System.out.println(call);
            }
        });
    }

    public void setIndex(int index) {
        mIndex.setValue(index);
    }

    public LiveData<String> getText() {
        return mText;
    }
}