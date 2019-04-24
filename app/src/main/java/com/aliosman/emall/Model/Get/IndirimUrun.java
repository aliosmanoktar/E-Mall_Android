package com.aliosman.emall.Model.Get;

import java.util.List;

public class IndirimUrun {

    private int ID;
    private String Adi;
    private float Fiyat;
    private List<String> Resimler;
    private float EskiFiyat;
    private int Oran;

    public float getEskiFiyat() {
        return EskiFiyat;
    }


    public int getOran() {
        return Oran;
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

}