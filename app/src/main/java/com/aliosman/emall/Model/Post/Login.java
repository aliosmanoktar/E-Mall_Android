package com.aliosman.emall.Model.Post;

import androidx.annotation.NonNull;

import com.google.gson.Gson;

public class Login {
    private String KullaniciAdi;
    private String Sifre;

    public Login(String kullaniciAdi, String sifre) {
        KullaniciAdi = kullaniciAdi;
        Sifre = sifre;
    }

    public String getKullaniciAdi() {
        return KullaniciAdi;
    }

    public String getSifre() {
        return Sifre;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
