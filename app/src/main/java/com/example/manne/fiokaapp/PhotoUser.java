package com.example.manne.fiokaapp;

/**
 * Created by manne on 16.1.2018.
 */

public class PhotoUser {
    int id;
    String username;
    String first_name;
    String userpic_url;

    public  PhotoUser(){

    };

    public PhotoUser(int id, String username, String first_name, String userpic_url) {
        this.id = id;
        this.username = username;
        this.first_name = first_name;
        this.userpic_url = userpic_url;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setUserpic_url(String userpic_url) {
        this.userpic_url = userpic_url;
    }

    public String getUserpic_url() {
        return userpic_url;
    }
}
