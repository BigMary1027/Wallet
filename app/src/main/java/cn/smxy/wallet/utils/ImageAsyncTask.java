package cn.smxy.wallet.utils;

import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import com.hhmedic.android.sdk.okhttputils.okhttp.OkHttpUtils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ImageAsyncTask extends AsyncTask<String,Integer,Map<String,Object>> {
    @Override
    protected void onPreExecute(){
        super.onPreExecute();
    }
    @Override
    protected void onPostExecute(Map<String,Object> map){

    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected Map<String, Object> doInBackground(String... strings) {
        return null;
    }
    private Map<String, Object> requestHttp(String webUrl){
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(webUrl).build();
        try {
            Map<String,Object> map = new HashMap<>();
            Response response = okHttpClient.newCall(request).execute();
            String verifiCode = response.header("verifiCode");
            map.put("bitmap", BitmapFactory.decodeStream(Objects.requireNonNull(response.body()).byteStream()));
            map.put("code",verifiCode);
            return map;
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
}
