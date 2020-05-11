package com.assignment.newsappnp.ui.main;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 *  Model for the List<Objects> form the response
 */
public class NewsListModel {
    @SerializedName("articles")
    @Expose
    private List<NewsModel> newsList = null;
    public List<NewsModel> getNewsArray() {
        return newsList;
    }

    public void setNewsArray(List<NewsModel> newsArray) {
        this.newsList = newsArray;
    }

}
