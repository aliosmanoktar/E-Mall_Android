package com.aliosman.emall.Model;

import com.google.gson.Gson;

public class Kullanici {

    private int ID;

    public int getID() {
        return ID;
    }

    public Kullanici setID(int ID) {
        this.ID = ID;
        return this;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}