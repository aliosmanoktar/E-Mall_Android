package com.aliosman.emall.Model.Get;

import com.google.gson.Gson;

public class Sepet {
    private int ID;
    private int UrunID;
    private int KullaniciID;
    private int Adet;
    private Urun Urun;

    public int getID() {
        return ID;
    }

    public int getUrunID() {
        return UrunID;
    }

    public int getAdet() {
        return Adet;
    }

    public Urun getUrun() {
        return Urun;
    }

    public int getKullaniciID() {
        return KullaniciID;
    }

    public Sepet setID(int ID) {
        this.ID = ID;
        return this;
    }

    public Sepet setUrunID(int urunID) {
        UrunID = urunID;
        return this;
    }

    public Sepet setKullaniciID(int kullaniciID) {
        KullaniciID = kullaniciID;
        return this;
    }

    public Sepet setAdet(int adet) {
        Adet = adet;
        return this;
    }

    public Sepet setUrun(com.aliosman.emall.Model.Get.Urun urun) {
        Urun = urun;
        return this;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

}