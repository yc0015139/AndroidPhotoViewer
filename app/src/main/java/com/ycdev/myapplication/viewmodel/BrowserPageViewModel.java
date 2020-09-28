package com.ycdev.myapplication.viewmodel;

import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ycdev.myapplication.model.Photo;
import com.ycdev.myapplication.utils.HttpHelper;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class BrowserPageViewModel extends ViewModel {
    private static final String URL = "https://jsonplaceholder.typicode.com/photos";

    public ArrayList<Photo> photos = new ArrayList<>(); // TODO: Add liveData

    public void loadPhotos() {
        new Thread(() -> {
            String json = HttpHelper.getResponse(URL);

            Gson gson = new Gson();
            final Type photosType = new TypeToken<ArrayList<Photo>>() {}.getType();
            photos = gson.fromJson(json, photosType);
        }).start();
    }
}
