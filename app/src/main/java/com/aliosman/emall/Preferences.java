package com.aliosman.emall;

import android.content.Context;
import android.content.SharedPreferences;
import com.aliosman.emall.Model.Kullanici;
import com.google.gson.Gson;

public class Preferences {

    public static Kullanici GetKullanici(Context c){
        String s=getPreferences(c).getString(degiskenler.KullaniciSharedString,null);
        return s==null ? null : new Gson().fromJson(s,Kullanici.class);
    }

    public static void SaveKullanici(Context c,Kullanici kullanici){
        SharedPreferences.Editor editor = getEditor(c);
        editor.putString(degiskenler.KullaniciSharedString,kullanici.toString());
        editor.commit();
    }

    public static void SaveKullanici(Context c,String s){
        SharedPreferences.Editor editor = getEditor(c);
        editor.putString(degiskenler.KullaniciSharedString,s);
        editor.commit();
    }

    private static  SharedPreferences.Editor getEditor(Context c){
        return getPreferences(c).edit();
    }

    private static SharedPreferences getPreferences(Context c){
        return c.getSharedPreferences(degiskenler.SharedNameString,Context.MODE_PRIVATE);
    }
}
