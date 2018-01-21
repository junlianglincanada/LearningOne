package com.example.junlianglin.learningone.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.junlianglin.framework.fragment.BaseFragment;
import com.example.junlianglin.learningone.R;
import com.example.junlianglin.learningone.adapter.NewsAdapter;
import com.example.junlianglin.learningone.model.News;
import com.example.junlianglin.learningone.view.NewsListView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@ContentView(R.layout.fragment_home_news)
public class HomeNewsFragment  extends BaseFragment {

    @ViewInject(R.id.listViewNews)
    private NewsListView listViewNews;

    private List<News> dataList = new ArrayList<>();

    private NewsAdapter newsAdapter ;


    //@ViewInject(R.layout.layout_home_news_more)
    private LinearLayout linearLayoutNewsMore;


    private boolean isLastRow = false;
    private boolean isMore = true;
    private boolean isLoading = false;

    private int pageIndex = 0;
    private int pageSize = 20;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    @Override
    protected void initParams() {
        linearLayoutNewsMore  = (LinearLayout)getLayoutInflater().inflate(R.layout.layout_home_news_more,null);

        loadData();


        newsAdapter = new NewsAdapter(context,dataList);

        listViewNews.addFooterView(linearLayoutNewsMore);
        //linearLayoutNewsFresh.setVisibility(View.GONE);
        //listViewNews.addHeaderView(linearLayoutNewsFresh);
        listViewNews.setAdapter(newsAdapter);
        listViewNews.setOnDeleteListener(new NewsListView.OnDeleteListener() {
            @Override
            public void onDelete(int index) {
                dataList.remove(index);
                newsAdapter.notifyDataSetChanged();
            }
        });




    }

    private void loadData(){
        for (int i=0;i<20;i++){
            News news = new News();
            news.setTitle("This  BBC news" + i);
            news.setDate(new Date().toString());
            dataList.add(news);
        }
    }




    //@Event(value = R.id.listViewNews ,type= ListView.OnScrollListener.class )
    private void onScrollStateChanged(AbsListView listView, int i) {
        System.out.println("dddddddddddddddddddd");
        if (isLastRow && i== AbsListView.OnScrollListener.SCROLL_STATE_IDLE){
            new AsyncTask<Void , Void, Void>() {
                @Override
                protected Void doInBackground(Void... voids) {
                    try{
                        Thread.sleep(1000);
                    }
                    catch(InterruptedException ex){
                        ex.printStackTrace();
                    }
                    loadData();
                    newsAdapter.refreshDatas(dataList);
                    return null;
                }
                @Override
                protected void onPostExecute(Void result){
                    super.onPostExecute(result);
                    newsAdapter.notifyDataSetChanged();

                }



            }.execute();
        }
    }

   //@Event(value = R.id.listViewNews ,type= ListView.OnScrollListener.class)
    private void onScroll(AbsListView listView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

        if(firstVisibleItem+visibleItemCount==totalItemCount && totalItemCount>0){
            isLastRow = true;
        }

    }

}
