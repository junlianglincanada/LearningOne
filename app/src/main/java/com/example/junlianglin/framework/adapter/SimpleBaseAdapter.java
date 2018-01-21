package com.example.junlianglin.framework.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.io.Serializable;
import java.util.List;

/**
 * Created by JunliangLin on 1/6/2018.
 */

public abstract class SimpleBaseAdapter<T> extends BaseAdapter {
    protected Context context = null;
    protected List<T> dataList = null;
    protected LayoutInflater layoutInflater = null;

    public    SimpleBaseAdapter(Context context,List<T> dataList){
        this.context = context;
        this.dataList = dataList;
        layoutInflater = LayoutInflater.from(context);
    }

    public void refreshDatas(List<T> dataList){
        this.dataList = dataList;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (dataList!=null && dataList.size()>0)
            return dataList.size();
        else
            return 0;
    }

    @Override
    public T getItem(int i) {
        if (dataList!=null && dataList.size()>0)
            return dataList.get(i);
        else
            return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public abstract View getView(int position, View view, ViewGroup viewGroup);


}
