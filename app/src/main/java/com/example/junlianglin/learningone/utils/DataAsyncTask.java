package com.example.junlianglin.learningone.utils;

import android.os.AsyncTask;

import com.example.junlianglin.learningone.model.Data;
import com.example.junlianglin.learningone.model.FormList;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

public class DataAsyncTask extends AsyncTask<Void, Void, Data> {

    private AsyncTaskResponse response;

    private final String token;
    private final String url;


    public DataAsyncTask(String token, String url, AsyncTaskResponse response){
        this.token = token;
        this.url = url;
        this.response = response;
    }

    @Override
    protected Data doInBackground(Void... voids) {
        try {
            Thread.sleep(2000);
            RequestParams requestParams = new RequestParams(url);
            requestParams.addHeader("Token",token);
            requestParams.addHeader("Content-Type","application/json");
            System.out.println("url = " + url + ":token=" + token);

            x.http().post(requestParams, new Callback.CommonCallback<Data>() {
                @Override
                public void onSuccess(Data result) {
                    System.out.println("success=");
                    response.processFinish(result);
                    //returnResult = result;
                }
                @Override
                public void onError(Throwable ex, boolean isOnCallback) {
                }

                @Override
                public void onCancelled(CancelledException cex) {
                }

                @Override
                public void onFinished() {
                }
            });
        } catch (InterruptedException e) {
            return null;
        }
        return null;
    }
}
