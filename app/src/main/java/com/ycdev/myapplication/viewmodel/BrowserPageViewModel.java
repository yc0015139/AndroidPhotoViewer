package com.ycdev.myapplication.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ycdev.myapplication.model.Photo;
import com.ycdev.myapplication.utils.HttpHelper;

import java.lang.reflect.Type;
import java.util.List;

public class BrowserPageViewModel extends ViewModel {
    private static final String URL = "https://jsonplaceholder.typicode.com/photos";

    public MutableLiveData<List<Photo>> photos;

    public void loadPhotos() {
        new Thread(() -> {
            String json = HttpHelper.getResponse(URL);
            List<Photo> parsedData = getParsedData(json);
            setPhotos(parsedData);
        }).start();
    }

    private List<Photo> getParsedData(String json) {
        Gson gson = new Gson();
        final Type photosType = new TypeToken<List<Photo>>() {}.getType();
        return gson.fromJson(json, photosType);
    }

    private void setPhotos(List<Photo> loadedPhotos) {
        photos.postValue(loadedPhotos);
    }

    public MutableLiveData<List<Photo>> getPhotos() {
        if (photos == null) {
            photos = new MutableLiveData<>();
        }
        return photos;
    }
}
