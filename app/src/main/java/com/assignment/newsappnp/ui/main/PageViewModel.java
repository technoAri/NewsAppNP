package com.assignment.newsappnp.ui.main;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.assignment.newsappnp.common.Strings;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *  View Model for for MainActivity
 *  TabsPageFragment uses the arrays and get methods in order to set the data to the view items
 */

public class PageViewModel extends ViewModel {

    private MutableLiveData<List<NewsModel>> newsList;
    private MutableLiveData<Integer> mIndex = new MutableLiveData<>();
    public static String[] TAB_TITLES = new String[Strings.mNumOfTabs];
    public static String[] newsTitles = new String[Strings.mNumOfTabs];
    public static String[] newsDescriptions = new String[Strings.mNumOfTabs];
    public static String[] timeStamp = new String[Strings.mNumOfTabs];
    public static String[] thumbnailURL = new String[Strings.mNumOfTabs];
    public static String[] urlToNews = new String[Strings.mNumOfTabs];
    public static String[] author = new String[Strings.mNumOfTabs];

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
        // Network call using retrofit
        Retrofit retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl(Strings.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        NewsApi newsApi = retrofit.create(NewsApi.class);
        Call<NewsListModel> call = newsApi.getNewsSources(Strings.country, Strings.apiKey);


        call.enqueue(new Callback<NewsListModel>() {
            @Override
            public void onResponse(Call<NewsListModel> call, Response<NewsListModel> response) {

                //finally we are setting the list to our MutableLiveData
                generateNoticeList((ArrayList<NewsModel>) response.body().getNewsArray());
            }

            @Override
            public void onFailure(Call<NewsListModel> call, Throwable t) {
                System.out.println(call);
            }
        });
    }

    // method to store data in the static arrays in order to set them in the Fragment
    private void generateNoticeList(ArrayList<NewsModel> newsArrayList) {
        System.out.println("NewsTitle11"+newsArrayList.get(0).getSource().name);
        for (int i = 0; i < 10; i++) {
            TAB_TITLES[i] = newsArrayList.get(i).getSource().name;
            newsTitles[i] = newsArrayList.get(i).getNewsTitle();
            newsDescriptions[i] = newsArrayList.get(i).getNewsDescription();
            thumbnailURL[i] = newsArrayList.get(i).getThumbnailImg();
            urlToNews[i] = newsArrayList.get(i).getUrl();
            author[i] = newsArrayList.get(i).getAuthor();

            try {
                timeStamp[i] = String.valueOf((new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX").
                        parse(newsArrayList.get(i).getTimestamp()))).substring(0, 16);
            } catch (ParseException e) {
                e.printStackTrace();
            }
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

    public LiveData<String> getUrlToNews() {
        LiveData<String> text = Transformations.map(mIndex, new Function<Integer, String>() {
            @Override
            public String apply(Integer input) {
                return urlToNews[input-1];
            }
        });
        return text;
    }

    public LiveData<String> getAuthor() {
        LiveData<String> text = Transformations.map(mIndex, new Function<Integer, String>() {
            @Override
            public String apply(Integer input) {
                return author[input-1];
            }
        });
        return text;
    }
}