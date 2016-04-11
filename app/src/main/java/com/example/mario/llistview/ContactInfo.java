package com.example.mario.llistview;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by mario on 01/04/2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContactInfo {
    int userId;
    String userName;
    String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
