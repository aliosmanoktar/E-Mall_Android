package com.aliosman.emall;

import okhttp3.MediaType;

public class degiskenler {

    private static final String Url="http://192.168.42.120/api/";
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    public static final String SharedNameString="Ayarlar";
    public static final String KullaniciSharedString="Kullanici";
    public static final String UrunKategoriBundleString ="KategoriID";
    public static final String UrunArananBundleString="arama";
    public static final String UrunShowIDBundleString="UrunID";
    public static final String StepperListenerBundle="StepperListener";
    public static final String ImageShowImagePosition="ImagePosition";
    public static final String ImageShowImageList="ImageList";
    public static final String PopulerUrunUrl=Url+"PopulerUrunler";
    public static final String IndirimUrunUrl=Url+"IndirimUrunler";
    public static final String LoginUrl=Url+"Login";
    public static final String RegisterUrl=Url+"Register";
    public static final String ResetPassword=Url+"ResetPassword";
    public static final String KategoriListUrl=Url+"Kategoriler?UstID=";
    public static final String KategoriListBackUrl=Url+"Kategoriler?BackID=";
    public static final String UrunLislemeKategoriUrl =Url+"Urunler?KategoriID=";
    public static final String UrunLislemeAramaUrl =Url+"Urunler?aranan=";
    public static final String UrunGerUrunUrl = Url+"Urunler?UrunID=";
    public static final String UrunGetBenzerUrunUrl = Url+"BenzerUrun?UrunID=";
    public static final String FavoriteGetUrl = Url+"Favorite?KullaniciID=";
    public static final String FavoriteDeleteIDUrl= Url+"Favorite?FavoriteID=";
    public static final String FavoriteClearAllUrl=Url+"Favorite?KullaniciID=";
    public static final String FavoritePostUrl = Url+"Favorite";
    public static final String FavoriteCheck=Url+"Favorite?KullaniciID=%d&UrunID=%d";
    public static final String SepetGetUrl = Url+"Sepet?KullaiciID=";
    public static final String SepetDeleteIDUrl = Url+"Sepet?SepetID=";
    public static final String SepetClearAllUrl =Url+"Sepet?KullaniciID=";
    public static final String SepetPostUrl = Url+"Sepet";
    public static final String SiparisPostUrl = Url+"Siparis";
    public static final String SipariGetUrl = Url+"Siparis?KullaniciID=";
    public static final String AdressGetUrl =Url+"Adres?KullaniciID=";
    public static final String KullaniciUpdateUrl= Url+"Update";
}