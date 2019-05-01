package com.aliosman.emall.Background;

import android.os.AsyncTask;
import android.util.Log;
import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ModelDelete extends AsyncTask<String,Void, Void> {

    private String TAG=getClass().getName();

    @Override
    protected Void doInBackground(String... strings) {
        String url = strings[0];
        OkHttpClient client = new OkHttpClient();
        Request request=new Request.Builder()
                .url(url)
                .delete()
                .build();
        Log.e(TAG, "doInBackground: URL => "+url);
        try {
            Response r=client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}