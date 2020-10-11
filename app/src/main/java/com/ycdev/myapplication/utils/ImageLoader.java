package com.ycdev.myapplication.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
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

    public static ImageLoader getInstance() {
        if (instance == null) {
            instance = new ImageLoader();
        }
        return instance;
    }

    public void loadImageByUrl(ImageView imageView, String url) {
        HttpHelper.getInstance().sendRequest(url, new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                Bitmap bitmap = getBitmapFromResponse(response);

                uiHandler.post(() -> {
                    imageView.setImageBitmap(bitmap);
                });
            }
        });
    }

    private Bitmap getBitmapFromResponse(Response response) {
        ResponseBody responseBody = response.body();
        InputStream inputStream = responseBody.byteStream();
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        return BitmapFactory.decodeStream(bufferedInputStream);
    }
}
