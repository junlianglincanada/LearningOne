package com.example.junlianglin.framework.listener;

import android.os.Handler;
import android.view.View;
import android.widget.AbsListView;

import com.example.junlianglin.learningone.model.News;

import java.util.List;

/**
 * Created by JunliangLin on 1/26/2018.
 */

public class MyOnScrollListener implements AbsListView.OnScrollListener {

    private int totalItemCount;
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
    private List<News> data;
    Handler handler = new Handler();

    public MyOnScrollListener(View footer, List<News> data) {
        this.footer = footer;
        this.data = data;
    }
    //设置接口回调的实例
    public void setOnLoadDataListener(OnloadDataListener listener) {
        this.listener = listener;
    }

    @Override
    public void onScrollStateChanged(AbsListView absListView, int scrollState) {
        if (!isLoading && firstItem == 0 && scrollState == SCROLL_STATE_IDLE) {
            //显示加载更多
            footer.setVisibility(View.VISIBLE);

            //模拟一个延迟两秒的刷新功能
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (listener != null) {
                        //开始加载更多数据
                        loadMoreData();
                        //回调设置ListView的数据
                        listener.onLoadData(data);
                        //加载完成后操作什么
                        loadComplete();
                    }
                }
            }, 2000);
        }

    }

    private void loadComplete() {
        isLoading = false;
        footer.setVisibility(View.GONE);

    }
    /**
     * 开始加载更多新数据，这里每次只更新三条数据
     */
    private void loadMoreData() {
        isLoading = true;
        News news = null;
        for (int i = 0; i < 3; i++) {
            news = new News();
            news.setDate("2018-01-23");
            news.setTitle("Today News");
            news.setComments(1);
            data.add(news);
        }
    }

    @Override
    public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        lastItem = firstVisibleItem + visibleItemCount;
        //实现上拉加载
        firstItem = firstVisibleItem;
        //总listView的item个数
        this.totalItemCount = totalItemCount;
    }

    public interface OnloadDataListener {
        void onLoadData(List<News> data);
    }
}
