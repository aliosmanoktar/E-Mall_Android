package com.aliosman.emall.Model.Get;

import com.google.gson.Gson;

public class Favorite {
    private int ID;
    private int UrunID;
    private int KullaniciID;
    private Urun Urun;

    public int getID() {
        return ID;
    }

    public int getUrunID() {
        return UrunID;
    }

    public Urun getUrun() {
        return Urun;
    }

    public int getKullaniciID() {
        return KullaniciID;
    }

    public Favorite setID(int ID) {
        this.ID = ID;
        return this;
    }

    public Favorite setUrunID(int urunID) {
        UrunID = urunID;
        return this;
    }

    public Favorite setKullaniciID(int kullaniciID) {
        KullaniciID = kullaniciID;
        return this;
    }

    public Favorite setUrun(com.aliosman.emall.Model.Get.Urun urun) {
        Urun = urun;
        return this;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}