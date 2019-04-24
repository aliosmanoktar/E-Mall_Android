package com.aliosman.emall;

import okhttp3.MediaType;

public class degiskenler {
    private static final String Url="http://10.52.177.111/api/";
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    public static final String PopulerUrunUrl=Url+"PopulerUrunler";
    public static final String IndirimUrunUrl=Url+"IndirimUrunler";
    public static final String LoginUrl=Url+"Login";
    public static final String RegisterUrl=Url+"Register";
    public static final String ResetPassword=Url+"ResetPassword";
    public static final String KategoriListUrl=Url+"Kategoriler?UstID=";
    public static final String KategoriListBackUrl=Url+"Kategoriler?BackID=";
}
