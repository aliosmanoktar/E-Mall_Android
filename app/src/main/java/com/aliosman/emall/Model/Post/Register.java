package com.aliosman.emall.Model.Post;

import com.google.gson.Gson;

import java.util.Date;

public class Register {
    private int ID;
    private String Telefon;
    private String KullaniciAdi;
    private String Email;
    private String Adi;
    private String Soyadi;
    private String Sifre;
    private Date DogumGunu;
    private String Cinsiyet;

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

    public String getSifre() {
        return Sifre;
    }

    public Date getDogumGunu() {
        return DogumGunu;
    }

    public String isCinsiyet() {
        return Cinsiyet;
    }

    public Register setID(int ID) {
        this.ID = ID;
        return this;
    }

    public Register setTelefon(String telefon) {
        Telefon = telefon;
        return this;
    }

    public Register setKullaniciAdi(String kullaniciAdi) {
        KullaniciAdi = kullaniciAdi;
        return this;
    }

    public Register setEmail(String email) {
        Email = email;
        return this;
    }

    public Register setAdi(String adi) {
        Adi = adi;
        return this;
    }

    public Register setSoyadi(String soyadi) {
        Soyadi = soyadi;
        return this;
    }

    public Register setSifre(String sifre) {
        Sifre = sifre;
        return this;
    }

    public Register setDogumGunu(Date dogumGunu) {
        DogumGunu = dogumGunu;
        return this;
    }

    public Register setCinsiyet(String cinsiyet) {
        Cinsiyet = cinsiyet;
        return this;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}