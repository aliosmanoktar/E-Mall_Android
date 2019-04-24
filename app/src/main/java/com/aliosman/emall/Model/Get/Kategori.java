package com.aliosman.emall.Model.Get;

public class Kategori {
    private int ID;
    private int UstID;
    private int ResimID;
    private String Adi;
    private String ResimPath;
    private String Aciklama;
    private boolean AltKategori;

    public int getID() {
        return ID;
    }

    public int getUstID() {
        return UstID;
    }

    public int getResimID() {
        return ResimID;
    }

    public boolean isAltKategori() {
        return AltKategori;
    }

    public String getAdi() {
        return Adi;
    }

    public String getResimPath() {
        return ResimPath;
    }

    public String getAciklama() {
        return Aciklama;
    }

    public Kategori setID(int ID) {
        this.ID = ID;
        return this;
    }

    public Kategori setUstID(int ustID) {
        UstID = ustID;
        return this;
    }

    public Kategori setResimID(int resimID) {
        ResimID = resimID;
        return this;
    }

    public Kategori setAdi(String adi) {
        Adi = adi;
        return this;
    }

    public Kategori setResimPath(String resimPath) {
        ResimPath = resimPath;
        return this;
    }

    public Kategori setAciklama(String aciklama) {
        Aciklama = aciklama;
        return this;
    }

    public Kategori setAltKategori(boolean altKategori) {
        AltKategori = altKategori;
        return this;
    }
}