package com.example.junlianglin.framework.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by JunliangLin on 1/6/2018.
 */

public abstract class BaseFragment extends Fragment {

    protected Context context;
    protected String token;

    private boolean injected = false;




    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        injected = true;
        //initParams();
        this.context = getContext();
        //View view = inflater.inflate(this,container,false);
        View view = x.view().inject(this, inflater, container);
        initParams();
        setRetainInstance(true);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
       if (!injected) {
           this.context = getContext();
            x.view().inject(this, this.getView());
            initParams();
        }

    }

    protected abstract void initParams();
}
