package com.example.mario.llistview;

import java.io.Serializable;

/**
 * Created by mario on 11/04/2016.
 */
public class UsersMessage implements Serializable {
    String from;
    String to;
    String Message;

    public UsersMessage(String from,String to,String Message){
        this.from=from;
        this.to=to;
        this.Message=Message;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getMessage() {
        return Message;
    }
}
