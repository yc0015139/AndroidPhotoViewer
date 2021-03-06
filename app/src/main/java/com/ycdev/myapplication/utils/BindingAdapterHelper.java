package com.ycdev.myapplication.utils;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

public class BindingAdapterHelper {

    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView imageView, String imageUrl) {
        imageView.setImageDrawable(null);
        ImageLoader.getInstance().loadImageByUrl(imageView, imageUrl);
    }
}
