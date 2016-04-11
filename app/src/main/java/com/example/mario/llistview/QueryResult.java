package com.example.mario.llistview;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

/**
 * Created by mario on 11/03/2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryResult {
    ResponseData responseData;

    public ResponseData getResponseData() {
        return responseData;
    }

    public void setResponseData(ResponseData responseData) {
        this.responseData = responseData;
    }
}
