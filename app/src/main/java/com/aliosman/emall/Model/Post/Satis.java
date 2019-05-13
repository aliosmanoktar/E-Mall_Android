package com.aliosman.emall.Model.Post;

import com.aliosman.emall.Model.Get.Sepet;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Satis {
    private int KullaniciID;
    private int AdresID;
    private List<Sepet> Items;
    private String Zaman;
    @SerializedName("SatisDurum")
    private String Durum;
    private String KargoKod;

    public String getKargoKod() {
        return KargoKod;
    }

    public String getZaman() {
        return Zaman;
    }

    public String getDurum() {
        return Durum;
    }

    public int getKullaniciID() {
        return KullaniciID;
    }

    public int getAdresID() {
        return AdresID;
    }

    public List<Sepet> getItems() {
        return Items;
    }

    public Satis setZaman(String zaman) {
        Zaman = zaman;
        return this;
    }

    public Satis setDurum(String durum) {
        Durum = durum;
        return this;
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

    public Satis setKargoKod(String kargoKod) {
        KargoKod = kargoKod;
        return this;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
