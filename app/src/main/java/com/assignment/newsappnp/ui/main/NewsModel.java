package com.assignment.newsappnp.ui.main;

import com.google.gson.annotations.SerializedName;

public class NewsModel {
    @SerializedName("urlToImage")
    private String thumbnailImg;
    @SerializedName("title")
    private String newsTitle;
    @SerializedName("description")
    private String newsDescription;
    @SerializedName("publishedAt")
    private String timestamp;

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    @SerializedName("source")
    Source source;

    public String getThumbnailImg() {
        return thumbnailImg;
    }

    public void setThumbnailImg(String thumbnailImg) {
        this.thumbnailImg = thumbnailImg;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNewsDescription() {
        return newsDescription;
    }

    public void setNewsDescription(String newsDescription) {
        this.newsDescription = newsDescription;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public class Source{
        @SerializedName("id")
        String id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @SerializedName("name")
        String name;
    }
}

