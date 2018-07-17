package com.example.junlianglin.framework.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import android.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;

import org.xutils.x;

/**
 * Created by JunliangLin on 1/5/2018.
 */

public abstract class BaseActivity extends AppCompatActivity {
    protected Dialog dialog;
    private boolean isCreated = false;
    public String token = "";
    protected boolean isTokenValid = false;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(getLayoutId() );






        x.view().inject(this);
        initParams();
        isCreated = true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isCreated){
            isCreated = false;
            //token = pref.getString("Token","");
            System.out.println("resume activity");
            //long tokenExpirySeconds = pref.getInt("Expiry",0);
            initParams();
        }
    }

    //protected abstract  int getLayoutId();

    protected abstract void initParams();


}
