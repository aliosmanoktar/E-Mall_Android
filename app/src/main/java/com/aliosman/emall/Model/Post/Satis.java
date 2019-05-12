package com.aliosman.emall.Model.Post;

import androidx.annotation.NonNull;

import com.aliosman.emall.Model.Get.Sepet;
import com.google.gson.Gson;

import java.util.List;

public class Satis {
    private int KullaniciID;
    private int AdresID;
    private List<Sepet> Items;

    public int getKullaniciID() {
        return KullaniciID;
    }

    public int getAdresID() {
        return AdresID;
    }

    public List<Sepet> getItems() {
        return Items;
    }

    public Satis setKullaniciID(int kullaniciID) {
        KullaniciID = kullaniciID;
        return this;
    }

    public Satis setAdresID(int adresID) {
        AdresID = adresID;
        return this;
    }

    public Satis setItems(List<Sepet> items) {
        Items = items;
        return this;
    }

    @NonNull
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
