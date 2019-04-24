package com.aliosman.emall.Background;

import android.os.AsyncTask;
import android.util.Log;
import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import com.aliosman.emall.Interface.PostInterface;
import com.aliosman.emall.Model.Get.ResponseBody;
import com.aliosman.emall.degiskenler;

public class ModelPost extends AsyncTask<String,Void, ResponseBody> {

    private PostInterface post;
    private String TAG=getClass().getName();

    public ModelPost(PostInterface post) {
        this.post = post;
    }

    @Override
    protected void onPreExecute() {
        post.Start();
    }

    /***
     * İlk String Url
     * İkinci Post Data
     * @param
     * @return
     */
    @Override
    protected ResponseBody doInBackground(String... strings) {
        String url = strings[0];
        OkHttpClient client = new OkHttpClient();
        Log.e(TAG, "doInBackground: OnPostData = "+strings[1] );
        RequestBody body= RequestBody.create(degiskenler.JSON,strings[1]);
        Request request=new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try {
            Response r=client.newCall(request).execute();
            String responseBody=r.body().string();
            return new ResponseBody().setCode(r.code()).setBody(responseBody);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(ResponseBody response) {
        post.Post(response.getCode(),response.getBody());
    }
}
