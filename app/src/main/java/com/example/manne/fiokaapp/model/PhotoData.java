package com.example.manne.fiokaapp.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by manne on 16.1.2018.
 */

public class PhotoData implements Serializable{
    public int id;
    public int user_id;
    public String name;
    public String image_url;


    public PhotoData(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getUser_id() {
        return user_id;
    }
}
