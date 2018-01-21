package com.example.junlianglin.framework.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.junlianglin.learningone.R;

import java.util.List;

/**
 * Created by JunliangLin on 1/20/2018.
 */

public class BaseListView<T> extends ListView implements View.OnTouchListener,ListView.OnScrollListener,
        GestureDetector.OnGestureListener{

    private int totaICount;
    //ListView最后的item项
    private int lastItem;
    //listview第一项
    private int firstItem;
    //用于判断当前是否在加载
    private boolean isLoading;
    //底部加载更多布局
    private View footer;
    //接口回调的实例
    private OnloadDataListener listener;

    //数据
    private List<T> data;

    //下面是滑动删除相关
    private GestureDetector mGestureDetector;
    private Boolean isDeleteShown = false;
    private ViewGroup itemLayout;
    private View deleteButton;
    private int selecetedItem;
    private OnDeleteListener mOnDeleteListener;


    public interface OnloadDataListener<T>  {
        void onLoadData(List<T> data);
    }
    public interface  OnDeleteListener {
        void onDelete(int index);
    }

    public void setOnDeleteListener(OnDeleteListener onDeleteListener) {
        mOnDeleteListener = onDeleteListener;
    }


    public BaseListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mGestureDetector = new GestureDetector(getContext(),this);
        setOnTouchListener(this);
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        if (!isDeleteShown) {

            selecetedItem = pointToPosition((int)motionEvent.getX(),(int)motionEvent.getY());
        }
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float velocityX, float velocityY) {
        if (!isDeleteShown && Math.abs(velocityX) > Math.abs(velocityY)) {
            deleteButton = LayoutInflater.from(getContext()).inflate(R.layout.delete_button,null);

            //TODO 设置删除按钮出现时的动画
            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(deleteButton,"alpha",0.0f,1.0f);
            objectAnimator.setDuration(5000);
            objectAnimator.start();

            // 回调MainActivty中的onDelete
            deleteButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemLayout.removeView(deleteButton);
                    deleteButton = null;
                    isDeleteShown = false;
                    mOnDeleteListener.onDelete(selecetedItem);
                }
            });
            // 在选择的item上添加deleteButton
            itemLayout = (ViewGroup) getChildAt(selecetedItem - getFirstVisiblePosition());
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            params.addRule(RelativeLayout.CENTER_VERTICAL);
            itemLayout.addView(deleteButton,params);
            isDeleteShown = true;
        }
        return false;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (isDeleteShown) {
            itemLayout.removeView(deleteButton);
            deleteButton = null;
            isDeleteShown = false;
            return false;
        } else {
            return mGestureDetector.onTouchEvent(motionEvent);
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {

    }

    @Override
    public void onScroll(AbsListView absListView, int i, int i1, int i2) {

    }
}
