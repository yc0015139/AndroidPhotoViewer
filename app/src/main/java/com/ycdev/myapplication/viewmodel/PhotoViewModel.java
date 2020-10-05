package com.ycdev.myapplication.viewmodel;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.ViewModel;

import com.ycdev.myapplication.model.Photo;
import com.ycdev.myapplication.utils.ImageLoader;

public class PhotoViewModel extends ViewModel {
    public String id, title, thumbnail;

    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView imageView, String imageUrl) {
        ImageLoader.getInstance().loadImageByUrl(imageView, imageUrl);
    }

    public void setPhoto(Photo photo) {
        this.id = String.valueOf(photo.getId());
        this.title = photo.getTitle();
        this.thumbnail = photo.getThumbnailUrl();
    }
}
