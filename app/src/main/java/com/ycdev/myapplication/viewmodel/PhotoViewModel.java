package com.ycdev.myapplication.viewmodel;

import androidx.lifecycle.ViewModel;

import com.ycdev.myapplication.model.Photo;

public class PhotoViewModel extends ViewModel {
    public String id, title, thumbnail;

    public void setPhoto(Photo photo) {
        this.id = String.valueOf(photo.getId());
        this.title = photo.getTitle();
        this.thumbnail = photo.getThumbnailUrl();
    }
}
