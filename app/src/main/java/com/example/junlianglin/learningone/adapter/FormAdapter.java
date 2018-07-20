package com.example.junlianglin.learningone.adapter;

import android.content.Context;

import com.example.junlianglin.framework.adapter.BaseViewHolder;
import com.example.junlianglin.framework.adapter.SimpleBaseAdapter;
import com.example.junlianglin.learningone.R;
import com.example.junlianglin.learningone.model.Form;
import com.example.junlianglin.learningone.model.Task;

import java.util.List;

public class FormAdapter extends SimpleBaseAdapter<Form> {

    public FormAdapter(Context context, List<Form> dataList, int layoutId) {
        super(context, dataList, layoutId);
    }

    @Override
    public void convert(BaseViewHolder viewHolder, Form form) {
        viewHolder.setText(R.id.textViewFormsName,form.getName());

    }
}
