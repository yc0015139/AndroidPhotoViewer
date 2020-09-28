package com.ycdev.myapplication.utils;

import android.util.Log;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpHelper {
    public static final int TIMEOUT = 30;

    private static final String TAG = HttpHelper.class.getSimpleName();

    public static String getResponse(String url) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
                .build();

        Request request = new Request.Builder().url(url).build();

        String retResponse = "";
        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                retResponse = response.body().string();
            } else {
                Log.d(TAG, "getResponse: Status code = " + response.message());
            }
        } catch (IOException e) {
            e.printStackTrace();
            Log.d(TAG, "getResponse: Some wrong on getResponse. e = " + e);
        }
        return retResponse;
    }
}
