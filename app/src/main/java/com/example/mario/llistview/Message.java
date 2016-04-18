package com.example.mario.llistview;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by mario on 16/04/2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Message {
    int id;
    int from;
    int to;
    String text;
    String date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
