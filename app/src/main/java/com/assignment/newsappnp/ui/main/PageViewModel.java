package com.assignment.newsappnp.ui.main;

import androidx.annotation.StringRes;
import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    private MutableLiveData<Integer> mIndex = new MutableLiveData<>();
    public static String[] TAB_TITLES = new String[10];
    public static String[] newsTitles = new String[10];
    public static String[] newsDescriptions = new String[10];
    public static String[] timeStamp = new String[10];
    public static String[] thumbnailURL = new String[10];
    public static String[] urlToNews = new String[10];
    public static String formattedTime;


    public LiveData<List<NewsModel>> getNewsSources() {
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
        for (int i = 0; i < 10; i++) {
//            finalNewsList.add(newsArrayList.get(i));
            TAB_TITLES[i] = newsArrayList.get(i).getSource().name;
            newsTitles[i] = newsArrayList.get(i).getNewsTitle();
            newsDescriptions[i] = newsArrayList.get(i).getNewsDescription();
            timeStamp[i] = newsArrayList.get(i).getTimestamp();
            thumbnailURL[i] = newsArrayList.get(i).getThumbnailImg();
            urlToNews[i] = newsArrayList.get(i).getUrl();
//            setUrlToNews(urlToNews[i], i);
            System.out.println("NewsTitle11"+timeStamp[i]);
        }
    }

    public void setIndex(int index) {
        mIndex.setValue(index);
    }

    public LiveData<String> getTitleText() {
        LiveData<String> text = Transformations.map(mIndex, new Function<Integer, String>() {
            @Override
            public String apply(Integer input) {
                return newsTitles[input-1];
            }
        });
        return text;
    }

    public LiveData<String> getDescription() {
        LiveData<String> text = Transformations.map(mIndex, new Function<Integer, String>() {
            @Override
            public String apply(Integer input) {
                return newsDescriptions[input-1];
            }
        });
        return text;
    }

    public LiveData<String> getTimeStamp() {
        LiveData<String> text = Transformations.map(mIndex, new Function<Integer, String>() {
            @Override
            public String apply(Integer input) {
                return timeStamp[input-1];
            }
        });
        return text;
    }

    public LiveData<String> getThumbnailUrl() {
        LiveData<String> text = Transformations.map(mIndex, new Function<Integer, String>() {
            @Override
            public String apply(Integer input) {
                return thumbnailURL[input-1];
            }
        });
        return text;
    }

//    public void setUrlToNews(String newsUrl, int position) {
//        urlToNews[position] = newsUrl;
//    }
    public LiveData<String> getUrlToNews() {
        LiveData<String> text = Transformations.map(mIndex, new Function<Integer, String>() {
            @Override
            public String apply(Integer input) {
                return urlToNews[input-1];
            }
        });
        return text;
    }
}