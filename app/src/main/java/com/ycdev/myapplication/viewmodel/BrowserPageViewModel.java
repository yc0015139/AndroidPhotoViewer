package com.ycdev.myapplication.viewmodel;

import androidx.lifecycle.ViewModel;

import com.ycdev.myapplication.model.Photo;
import com.ycdev.myapplication.utils.HttpHelper;

import java.util.ArrayList;

public class BrowserPageViewModel extends ViewModel {
    private static final String URL = "https://jsonplaceholder.typicode.com/photos";

    public ArrayList<Photo> photos = new ArrayList<>();

    public void loadPhotos() {
        new Thread(() -> {
            String response = HttpHelper.getResponse(URL);
        }).start();
    }
}
