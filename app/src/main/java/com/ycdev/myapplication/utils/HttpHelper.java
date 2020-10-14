package com.ycdev.myapplication.utils;

import java.util.concurrent.TimeUnit;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class HttpHelper {
    public static final int TIMEOUT_SECONDS = 30;
    public static final int CONNECTION_TIMEOUT_MILLISECONDS = 200;

    public static HttpHelper instance;

    private OkHttpClient client = new OkHttpClient().newBuilder()
            .connectTimeout(CONNECTION_TIMEOUT_MILLISECONDS, TimeUnit.MILLISECONDS)
            .readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .build();

    public static HttpHelper getInstance() {
        if (instance == null) {
            instance = new HttpHelper();
        }
        return instance;
    }

    public void sendRequest(String url, Callback callback) {
        Request request = new Request.Builder().url(url).build();

        client.newCall(request).enqueue(callback);
    }
}
