package com.aliosman.emall.Model;

import com.google.gson.Gson;

public class Kullanici {

    private int ID;
    private String Telefon;
    private String KullaniciAdi;
    private String Email;
    private String Adi;
    private String Soyadi;

    public int getID() {
        return ID;
    }

    public String getTelefon() {
        return Telefon;
    }

    public String getKullaniciAdi() {
        return KullaniciAdi;
    }

    public String getEmail() {
        return Email;
    }

    public String getAdi() {
        return Adi;
    }

    public String getSoyadi() {
        return Soyadi;
    }

    public Kullanici setTelefon(String telefon) {
        Telefon = telefon;
        return this;
    }

    public Kullanici setKullaniciAdi(String kullaniciAdi) {
        KullaniciAdi = kullaniciAdi;
        return this;
    }

    public Kullanici setEmail(String email) {
        Email = email;
        return this;
    }

    public Kullanici setAdi(String adi) {
        Adi = adi;
        return this;
    }

    public Kullanici setSoyadi(String soyadi) {
        Soyadi = soyadi;
        return this;
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