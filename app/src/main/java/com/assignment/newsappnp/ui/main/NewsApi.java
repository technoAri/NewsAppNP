package com.assignment.newsappnp.ui.main;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApi {
    String BASE_URL = "http://newsapi.org/v2/";
//    String apiKey = "4a1eabbbdffb429ebbcd12771e5d684c";

    @GET("top-headlines")
    Call<NewsList> getNewsSources(@Query("country") String country, @Query("apiKey") String apiKey);
}
