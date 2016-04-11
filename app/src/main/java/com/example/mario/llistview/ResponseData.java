package com.example.mario.llistview;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

/**
 * Created by mario on 11/03/2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseData {
    ArrayList<ResultItems> results;

    public ArrayList<ResultItems> getResults() {
        return results;
    }

    public void setResults(ArrayList<ResultItems> results) {
        this.results = results;
    }
}
