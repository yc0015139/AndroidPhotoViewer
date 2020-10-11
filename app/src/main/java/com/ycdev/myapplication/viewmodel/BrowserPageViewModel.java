package com.ycdev.myapplication.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ycdev.myapplication.interfaces.OnResponseListener;
import com.ycdev.myapplication.model.Photo;
import com.ycdev.myapplication.utils.BaseHttpAsyncTask;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import okhttp3.ResponseBody;

public class BrowserPageViewModel extends ViewModel implements OnResponseListener<String> {
    private static final String URL = "https://jsonplaceholder.typicode.com/photos";

    private MutableLiveData<List<Photo>> photos = new MutableLiveData<>();

    public void loadPhotos() {
        new RequestTask(this).execute(URL);
    }

    public MutableLiveData<List<Photo>> getPhotos() {
        return photos;
    }

    @Override
    public void onResponse(String string) {
        List<Photo> parsedData = getParsedData(string);
        setPhotos(parsedData);
    }

    private List<Photo> getParsedData(String json) {
        Gson gson = new Gson();
        final Type photosType = new TypeToken<List<Photo>>() {
        }.getType();
        return gson.fromJson(json, photosType);
    }

    private void setPhotos(List<Photo> loadedPhotos) {
        photos.postValue(loadedPhotos);
    }

    private class RequestTask extends BaseHttpAsyncTask {
        public RequestTask(OnResponseListener listener) {
            super(listener);
        }

        @Override
        protected String getResponseOnBody(ResponseBody responseBody) {
            if (responseBody == null) {
                return null;
            }

            String respon = null;
            try {
                respon = responseBody.string();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return respon;
        }
    }
}
