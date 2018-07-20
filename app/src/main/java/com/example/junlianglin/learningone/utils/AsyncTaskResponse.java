package com.example.junlianglin.learningone.utils;

import com.example.junlianglin.learningone.model.ReturnResult;

public  interface AsyncTaskResponse {
    abstract void  processFinish(ReturnResult output);
}
