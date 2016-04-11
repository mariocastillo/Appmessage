package com.example.mario.llistview;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by mario on 11/03/2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResultItems {
    private String url;
    private String title;

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
