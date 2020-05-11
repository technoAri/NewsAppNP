package com.assignment.newsappnp.ui.main;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApi {
    @GET("top-headlines")
    Call<NewsListModel> getNewsSources(@Query("country") String country, @Query("apiKey") String apiKey);
}
