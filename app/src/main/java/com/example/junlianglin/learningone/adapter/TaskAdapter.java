package com.example.junlianglin.learningone.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.junlianglin.framework.adapter.BaseViewHolder;
import com.example.junlianglin.framework.adapter.SimpleBaseAdapter;
import com.example.junlianglin.learningone.R;
import com.example.junlianglin.learningone.model.News;
import com.example.junlianglin.learningone.model.Task;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by JunliangLin on 1/8/2018.
 */

public class TaskAdapter extends SimpleBaseAdapter<Task> {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");

    public TaskAdapter(Context context, List<Task> dataList, int layoutId){
        super(context,dataList,layoutId);
    }


    @Override
    public void convert(BaseViewHolder viewHolder, Task task) {
        viewHolder.setText(R.id.textViewTaskAddress,task.getBuildingAddress() + "#" + task.getUnitNumber());
        viewHolder.setText(R.id.textViewTaskRentalType,task.getRentalType());
        viewHolder.setText(R.id.textViewStatus,task.getStatus());
        viewHolder.setText(R.id.textViewDate,sdf.format(task.getInspectedDate()));
    }

}
