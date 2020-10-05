package com.ycdev.myapplication.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ycdev.myapplication.model.Photo;
import com.ycdev.myapplication.utils.HttpHelper;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class BrowserPageViewModel extends ViewModel {
    private static final String URL = "https://jsonplaceholder.typicode.com/photos";

    private MutableLiveData<List<Photo>> photos = new MutableLiveData<>();

    public void loadPhotos() {
        HttpHelper.sendRequest(URL, new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String json = response.body().string();
                List<Photo> parsedData = getParsedData(json);
                setPhotos(parsedData);
            }
        });
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
        return photos;
    }
}
