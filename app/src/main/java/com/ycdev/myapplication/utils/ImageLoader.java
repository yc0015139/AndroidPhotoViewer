package com.ycdev.myapplication.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.util.LruCache;
import android.widget.ImageView;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class ImageLoader {
    private static ImageLoader instance;
    private Handler uiHandler = new Handler();

    private int runtimeMaxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
    private LruCache lruCache = new LruCache<String, Bitmap>(runtimeMaxMemory) {
        @Override
        protected int sizeOf(String key, Bitmap value) {
            return value.getByteCount();
        }
    };

    public static ImageLoader getInstance() {
        if (instance == null) {
            instance = new ImageLoader();
        }
        return instance;
    }

    public void loadImageByUrl(ImageView imageView, String url) {
        Bitmap readBitmap = (Bitmap) lruCache.get(url);

        if (readBitmap == null) {
            sendRequestAndUpdate(imageView, url);
        } else {
            updateImageViewByBitmap(imageView, readBitmap);
        }
    }

    private void sendRequestAndUpdate(ImageView imageView, String url) {
        HttpHelper.sendRequest(url, new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                Bitmap bitmap = getBitmapFromResponse(response);
                lruCache.put(url, bitmap);
                updateImageViewByBitmap(imageView, bitmap);
            }
        });
    }

    private Bitmap getBitmapFromResponse(Response response) {
        ResponseBody responseBody = response.body();
        InputStream inputStream = responseBody.byteStream();
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        return BitmapFactory.decodeStream(bufferedInputStream);
    }

    private void updateImageViewByBitmap(ImageView imageView, Bitmap bitmap) {
        uiHandler.post(() -> {
            imageView.setImageBitmap(bitmap);
        });
    }
}
