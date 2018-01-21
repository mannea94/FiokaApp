package com.example.manne.fiokaapp.model;

import com.example.manne.fiokaapp.model.PhotoData;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by manne on 16.1.2018.
 */

public class PhotoModel implements Serializable {
    int id;
    public String username;
    public String first_name;
    public String userpic_url;
    public ArrayList<PhotoData> photos = new ArrayList<>();
}
