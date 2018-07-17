package com.example.junlianglin.framework.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import org.xutils.x;

/**
 * Created by JunliangLin on 1/26/2018.
 */

public class BaseViewHolder {
    private SparseArray<View> views;

    private int position;
    private View convertView;

    public BaseViewHolder(Context context, ViewGroup parent, int layoutId, int position) {
        this.position = position;
        views = new SparseArray<View>();
        convertView = LayoutInflater.from(context).inflate(layoutId, parent, false);

        convertView.setTag(this);
    }

    public static BaseViewHolder getViewHolder(Context context, View view
            , ViewGroup viewGroup, int layoutId, int position) {
        if (view == null) {
            BaseViewHolder holder =  new BaseViewHolder(context, viewGroup, layoutId, position);
            x.view().inject(holder,view);
            return holder;
        } else {

            BaseViewHolder holder = (BaseViewHolder) view.getTag();
            holder.position = position;
            return holder;
        }
    }

    public <T extends View> T getView(int viewId) {

        View view = views.get(viewId);
        if (view == null) {
            view = convertView.findViewById(viewId);
            views.append(viewId, view);
        }
        return (T) view;
    }

    public View getConvertView() {
        return convertView;
    }

    public BaseViewHolder setText(int id, String str) {
        ((TextView)getView(id)).setText(str);
        return this;
    }

    public BaseViewHolder setCheckBoxText(int id, String str) {
        ((CheckBox)getView(id)).setText(str);
        return this;
    }

    public BaseViewHolder setButtonText(int id, String str) {
        ((Button)getView(id)).setText(str);
        return this;
    }

}
