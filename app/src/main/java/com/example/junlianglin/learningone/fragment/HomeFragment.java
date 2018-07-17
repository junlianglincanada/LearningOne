package com.example.junlianglin.learningone.fragment;

import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.junlianglin.framework.fragment.BaseFragment;
import com.example.junlianglin.learningone.R;
import com.example.junlianglin.learningone.adapter.TaskAdapter;
import com.example.junlianglin.learningone.model.Task;
import com.example.junlianglin.learningone.model.TaskList;
import com.example.junlianglin.learningone.utils.Preferences;
import com.example.junlianglin.learningone.utils.ServerUrl;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;


@ContentView(R.layout.fragment_home)
public class HomeFragment extends BaseFragment {

    @ViewInject(R.id.listViewTasks)
    private ListView listViewTasks;

    //@ViewInject(R.id.footer)
    private LinearLayout footer;

    private TaskAdapter tasksAdapter ;

    private List<Task> dataList = new ArrayList<>();

    private int pageNumber = 1;
    private boolean hasNextPage = false;
    private TaskListDataTask mAuthTask = null;
    private boolean isLoading = false;



    @Override
    protected void initParams() {
        Preferences preferences = new Preferences();
        token = preferences.getAccessToken();
        footer  = (LinearLayout)getLayoutInflater().inflate(R.layout.layout_foot,null,false);

        listViewTasks.addFooterView(footer);
        footer.setVisibility(View.INVISIBLE);
        tasksAdapter = new TaskAdapter(context,dataList,R.layout.layout_home_tasks);
        listViewTasks.setAdapter(tasksAdapter);

        listViewTasks.setOnScrollListener(new AbsListView.OnScrollListener(){
            private int state;

            @Override
            public void onScrollStateChanged(AbsListView absListView, int scrollState) {
                state = scrollState;
            }

            @Override
            public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

                if (!isLoading && hasNextPage && state==1 &&
                        (firstVisibleItem+visibleItemCount>=totalItemCount)){
                    footer.setVisibility(View.VISIBLE);
                    isLoading = true;
                    mAuthTask = new TaskListDataTask(pageNumber, token);
                    mAuthTask.execute((Void) null);
                }
            }
        });

        mAuthTask = new TaskListDataTask(pageNumber, token);
        mAuthTask.execute((Void) null);



    }


    private void loadData(TaskList list){
        hasNextPage = list.pagination.hasNextPage;
        pageNumber = list.pagination.pageNumber+1;
        isLoading = false;
        if (list!=null && list.data.size()>0){
            for(int i=0;i<list.data.size();i++){
                dataList.add(list.data.get(i));
            }
        }
    }

    public class TaskListDataTask extends AsyncTask<Void, Void, TaskList> {
        private int pageNumber;
        private final String token;

        public void setPageNumber(int pageNumber){
            this.pageNumber = pageNumber;
        }

        public TaskListDataTask(int pageNumber,String token){
            this.pageNumber = pageNumber;
            this.token = token;
        }
        @Override
        protected TaskList doInBackground(Void... voids) {
            try {
                Thread.sleep(2000);
                RequestParams requestParams = new RequestParams(
                        ServerUrl.REMOTEURL + ":" + ServerUrl.REMOTEPORT + ServerUrl.TASK_LIST
                                + "?pageNo="+ pageNumber +"&pageSize=15");
                requestParams.addHeader("Token",token);
                requestParams.addHeader("Content-Type","application/json");

                x.http().post(requestParams, new Callback.CommonCallback<TaskList>() {
                    @Override
                    public void onSuccess(TaskList result) {
                        loadData(result);
                    }
                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                    }

                    @Override
                    public void onCancelled(CancelledException cex) {
                    }

                    @Override
                    public void onFinished() {
                        tasksAdapter.notifyDataSetChanged();
                    }
                });
            } catch (InterruptedException e) {
                return null;
            }
            return null;
        }

        @Override
        protected void onPostExecute(final TaskList success) {
            super.onPostExecute(success);
            footer.setVisibility(View.INVISIBLE);
        }
    }
}
