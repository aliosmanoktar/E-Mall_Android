package com.aliosman.emall.Model.Get;

import com.google.gson.annotations.SerializedName;

public class Adres {

    private int ID;
    private String AcikAdres;

    @SerializedName("AdresAdi")
    private String Adi;

    public Adres setID(int ID) {
        this.ID = ID;
        return this;
    }

    public Adres setAcikAdres(String acikAdres) {
        AcikAdres = acikAdres;
        return this;
    }

    public Adres setAdi(String adi) {
        Adi = adi;
        return this;
    }

    public int getID() {
        return ID;
    }

    public String getAcikAdres() {
        return AcikAdres;
    }

    public String getAdi() {
        return Adi;
    }
}
