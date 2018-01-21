package com.example.manne.fiokaapp.model2;

import java.io.Serializable;
import java.nio.file.SecureDirectoryStream;
import java.util.ArrayList;

/**
 * Created by manne on 18.1.2018.
 */

public class BlogUser implements Serializable {
    public int id;
    public String sol;
    public String img_src;
    public String earth_date;


    public BlogUser(){

    }

    public String getImg_src() {
        return img_src;
    }

    public void setImg_src(String img_src) {
        this.img_src = img_src;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEarth_date() {
        return earth_date;
    }

    public void setEarth_date(String earth_date) {
        this.earth_date = earth_date;
    }

    public String getSol() {
        return sol;
    }

    public void setSol(String sol) {
        this.sol = sol;
    }
}
