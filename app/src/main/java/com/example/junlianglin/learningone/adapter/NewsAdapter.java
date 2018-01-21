package com.example.junlianglin.learningone.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.junlianglin.framework.adapter.SimpleBaseAdapter;
import com.example.junlianglin.learningone.R;
import com.example.junlianglin.learningone.model.News;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Created by JunliangLin on 1/8/2018.
 */

public class NewsAdapter extends SimpleBaseAdapter<News> {

    public NewsAdapter(Context context,List<News> dataList){
        super(context,dataList);
    }
    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        EntityHolder entityHolder;
        if (view==null){
            entityHolder = new EntityHolder();
            view = layoutInflater.inflate(R.layout.layout_home_news,null);
            x.view().inject(entityHolder,view);
            view.setTag(entityHolder);
        }
        else {
            entityHolder = (EntityHolder)view.getTag();
        }

        entityHolder.textViewNewsTitle.setText(dataList.get(position).getTitle());
        entityHolder.textViewDate.setText(dataList.get(position).getDate());

        return view;
    }
    private class EntityHolder{
        @ViewInject(R.id.textViewNewsTitle)
        TextView textViewNewsTitle;

        @ViewInject(R.id.textViewDate)
        TextView textViewDate;


    }
}
