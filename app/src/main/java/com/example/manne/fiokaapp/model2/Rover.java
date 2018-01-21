package com.example.manne.fiokaapp.model2;

import java.io.Serializable;

/**
 * Created by manne on 20.1.2018.
 */

public class Rover implements Serializable {
    int id;
    String name;

    public Rover(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
