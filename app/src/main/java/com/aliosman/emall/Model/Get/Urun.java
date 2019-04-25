package com.aliosman.emall.Model.Get;

import java.util.List;

public class Urun {

    private int ID;
    private String Adi;
    private float Fiyat;
    private List<String> Resimler;
    private float EskiFiyat;
    private int Oran;

    public Urun(String adi, float fiyat) {
        Adi = adi;
        Fiyat = fiyat;
    }

    public int getID() {
        return ID;
    }

    public String getAdi() {
        return Adi;
    }

    public float getFiyat() {
        return Fiyat;
    }

    public List<String> getResimler() {
        return Resimler;
    }

    public float getEskiFiyat() {
        return EskiFiyat;
    }

    public int getOran() {
        return Oran;
    }
}
