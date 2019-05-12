package com.aliosman.emall.Background;

import android.os.AsyncTask;
import android.util.Log;
import com.aliosman.emall.Interface.DownloadSingleInterface;
import com.google.gson.Gson;
import java.lang.reflect.Type;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ModelDownloadSingle<T> extends AsyncTask<String,Void, T> {

    private final String TAG =getClass().getName();
    private final Class JsonParseClassType;
    private DownloadSingleInterface<T> finish;

    public ModelDownloadSingle(Class JsonParseClassType, DownloadSingleInterface finish){
        this.JsonParseClassType = JsonParseClassType;
        this.finish=finish;
    }

    @Override
    protected void onPreExecute() {
        finish.Start();
    }

    @Override
    protected T doInBackground(String... strings){
        String url = strings[0];
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        Request request = new Request.Builder()
                .url(url)
                .header("Accept","application/json")
                .build();
        OkHttpClient client = httpClient.connectTimeout(15, TimeUnit.SECONDS).build();
        try{

            Response response = client.newCall(request).execute();
            String s = response.body().string();
            return new Gson().fromJson(s, (Type) JsonParseClassType);

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(T item) {
        finish.Complete(item);
    }
}