package com.aliosman.emall.Background;

import android.os.AsyncTask;
import android.util.Log;
import com.aliosman.emall.Interface.DownloadInterface;
import com.google.gson.Gson;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ModelDownloadList<T> extends AsyncTask<String,Void, List<T>> {

    private final String TAG =getClass().getName();
    private final Class JsonParseClassType;
    private DownloadInterface<T> finish;

    public ModelDownloadList(Class JsonParseClassType, DownloadInterface finish){
        this.JsonParseClassType = JsonParseClassType;
        this.finish=finish;
    }

    @Override
    protected void onPreExecute() {
        finish.Start();
    }

    @Override
    protected List<T> doInBackground(String... strings){
        String url = strings[0];
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        Request request = new Request.Builder()
                .url(url)
                .header("Accept","application/json")
                .build();
        Log.e(TAG, "doInBackground: GetUrl => "+url);
        OkHttpClient client = httpClient.connectTimeout(15, TimeUnit.SECONDS).build();
        try{

            Response response = client.newCall(request).execute();
            String s = response.body().string();
            return Arrays.asList(new Gson().fromJson(s, (Type) JsonParseClassType));

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(List<T> ts) {
        finish.Complete(ts);
    }
}