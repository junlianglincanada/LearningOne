package com.example.junlianglin.learningone.utils;

import android.os.AsyncTask;
import android.view.View;

import com.example.junlianglin.learningone.model.ReturnResult;
import com.example.junlianglin.learningone.model.TaskList;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

public class TaskAsyncTask extends AsyncTask<Void, Void, TaskList> {

    private AsyncTaskResponse response;

    private int pageNumber;
    private final String token;
    private final String url;

    public void setPageNumber(int pageNumber){
        this.pageNumber = pageNumber;
    }

    public TaskAsyncTask(int pageNumber, String token, String url, AsyncTaskResponse response){
        this.pageNumber = pageNumber;
        this.token = token;
        this.url = url;
        this.response = response;
    }


    @Override
    protected TaskList doInBackground(Void... voids) {
        try {
            Thread.sleep(2000);
            RequestParams requestParams = new RequestParams(url);
            requestParams.addHeader("Token",token);
            requestParams.addHeader("Content-Type","application/json");
            System.out.println("url = " + url);

            x.http().post(requestParams, new Callback.CommonCallback<TaskList>() {
                @Override
                public void onSuccess(TaskList result) {
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
