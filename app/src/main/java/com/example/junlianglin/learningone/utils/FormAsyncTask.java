package com.example.junlianglin.learningone.utils;

import android.os.AsyncTask;

import com.example.junlianglin.learningone.model.FormList;
import com.example.junlianglin.learningone.model.TaskList;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

public class FormAsyncTask extends AsyncTask<Void, Void, FormList> {

    private AsyncTaskResponse response;

    private final String token;
    private final String url;


    public FormAsyncTask(String token, String url, AsyncTaskResponse response){
        this.token = token;
        this.url = url;
        this.response = response;
    }


    @Override
    protected FormList doInBackground(Void... voids) {
        try {
            Thread.sleep(2000);
            RequestParams requestParams = new RequestParams(url);
            requestParams.addHeader("Token",token);
            requestParams.addHeader("Content-Type","application/json");
            System.out.println("url = " + url);

            x.http().post(requestParams, new Callback.CommonCallback<FormList>() {
                @Override
                public void onSuccess(FormList result) {
                    System.out.println("success=" + result.getData().size());
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
