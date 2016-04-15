package com.example.mario.llistview;

import java.io.Serializable;

/**
 * Created by mario on 11/04/2016.
 */
public class UsersMessage implements Serializable {
    int from;
    int to;
    String text;

    public UsersMessage(int from,int to,String Message){
        this.from=from;
        this.to=to;
        this.text=Message;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public int getFrom() {
        return from;
    }

    public int getTo() {
        return to;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
