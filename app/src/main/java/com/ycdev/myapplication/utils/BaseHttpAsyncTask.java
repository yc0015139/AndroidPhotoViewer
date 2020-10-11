package com.ycdev.myapplication.utils;

import android.os.AsyncTask;

import com.ycdev.myapplication.interfaces.OnResponseListener;

import okhttp3.Response;
import okhttp3.ResponseBody;

public abstract class BaseHttpAsyncTask extends AsyncTask<String, Void, Response> {
    private OnResponseListener listener;

    public BaseHttpAsyncTask(OnResponseListener listener) {
        this.listener = listener;
    }

    @Override
    protected Response doInBackground(String... urls) {
        return HttpHelper.getInstance().sendRequest(urls[0]);
    }

    @Override
    protected void onPostExecute(Response response) {
        ResponseBody responseBody = response.body();
        listener.onResponse(getResponseOnBody(responseBody));
    }

    protected abstract Object getResponseOnBody(ResponseBody responseBody);
}
