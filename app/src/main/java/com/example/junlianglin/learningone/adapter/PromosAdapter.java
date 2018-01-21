package com.example.junlianglin.learningone.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.junlianglin.learningone.R;

import java.util.List;

/**
 * Created by JunliangLin on 1/10/2018.
 */

public class PromosAdapter<T>  extends  RecyclerView.Adapter{
    private List<T> dataList;

    public static interface OnRecyclerViewListener {
         void onItemClick(int position);
         boolean onItemLongClick(int position);
     }

             private OnRecyclerViewListener onRecyclerViewListener;

             public void setOnRecyclerViewListener(OnRecyclerViewListener onRecyclerViewListener) {
                 this.onRecyclerViewListener = onRecyclerViewListener;
             }

    public PromosAdapter(List<T> dataList){
        this.dataList = dataList;
    }


    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemCount() {
        if (dataList!=null && dataList.size()>0)
            return dataList.size();
        return 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_promos_list, null);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);
        return new PromosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    class PromosViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{
        public View rootView;
        public TextView textViewPromosName;
        public TextView textViewPromosAddress;
        public int position;
        @Override
        public void onClick(View view) {

        }

        @Override
        public boolean onLongClick(View view) {
            return false;
        }

        public PromosViewHolder(View itemView) {
            super(itemView);
        }
    }
}
