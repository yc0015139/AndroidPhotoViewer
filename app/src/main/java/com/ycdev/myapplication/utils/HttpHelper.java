package com.ycdev.myapplication.utils;

import java.util.concurrent.TimeUnit;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class HttpHelper {
    public static final int TIMEOUT = 30;

    private static final String TAG = HttpHelper.class.getSimpleName();

    public static void sendRequest(String url, Callback callback) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
                .build();

        Request request = new Request.Builder().url(url).build();

        client.newCall(request).enqueue(callback);
    }
}
