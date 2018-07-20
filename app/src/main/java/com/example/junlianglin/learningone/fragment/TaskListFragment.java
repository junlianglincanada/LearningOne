package com.example.junlianglin.learningone.fragment;

import android.view.View;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.junlianglin.framework.fragment.BaseFragment;
import com.example.junlianglin.learningone.R;
import com.example.junlianglin.learningone.adapter.TaskAdapter;
import com.example.junlianglin.learningone.model.ReturnResult;
import com.example.junlianglin.learningone.model.Task;
import com.example.junlianglin.learningone.model.TaskList;
import com.example.junlianglin.learningone.utils.TaskAsyncTask;
import com.example.junlianglin.learningone.utils.AsyncTaskResponse;
import com.example.junlianglin.learningone.utils.Preferences;
import com.example.junlianglin.learningone.utils.ServerUrl;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;


@ContentView(R.layout.fragment_tasklist)
public class TaskListFragment extends BaseFragment {

    @ViewInject(R.id.listViewTasks)
    private ListView listViewTasks;

    //@ViewInject(R.id.footer)
    private LinearLayout footer;

    private TaskAdapter tasksAdapter ;

    private List<Task> dataList = new ArrayList<>();

    private int pageNumber = 1;
    private boolean hasNextPage = false;
    //private TaskListDataTask mAuthTask = null;
    private TaskAsyncTask mAuthTask = null;
    private boolean isLoading = false;



    @Override
    protected void initParams() {
        Preferences preferences = new Preferences();
        token = preferences.getAccessToken();
        footer  = (LinearLayout)getLayoutInflater().inflate(R.layout.layout_foot,null,false);

        listViewTasks.addFooterView(footer);
        footer.setVisibility(View.INVISIBLE);
        tasksAdapter = new TaskAdapter(context,dataList,R.layout.layout_tasks);
        listViewTasks.setAdapter(tasksAdapter);
        String url = ServerUrl.REMOTEURL + ":" + ServerUrl.REMOTEPORT + ServerUrl.TASK_LIST
                + "?pageNo="+ pageNumber +"&pageSize=15";

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

                    final String thisUrl = ServerUrl.REMOTEURL + ":" + ServerUrl.REMOTEPORT + ServerUrl.TASK_LIST
                            + "?pageNo="+ pageNumber +"&pageSize=15";
                    footer.setVisibility(View.VISIBLE);
                    isLoading = true;
                    mAuthTask = new TaskAsyncTask(pageNumber, token,thisUrl,new AsyncTaskResponse(){
                        @Override
                        public void processFinish(ReturnResult output) {
                            loadData((TaskList) output);
                            tasksAdapter.notifyDataSetChanged();
                        }
                    });
                    mAuthTask.execute((Void) null);
                }
            }
        });

        mAuthTask = new TaskAsyncTask(pageNumber, token,url,new AsyncTaskResponse(){
            @Override
            public void processFinish(ReturnResult output) {
                loadData((TaskList) output);
                tasksAdapter.notifyDataSetChanged();

            }
        });
        mAuthTask.execute((Void) null);




    }


    private void loadData(TaskList list){
        hasNextPage = list.getPagination().hasNextPage;
        pageNumber = list.getPagination().pageNumber+1;
        isLoading = false;
        if (list!=null && list.getData().size()>0){
            for(int i=0;i<list.getData().size();i++){
                dataList.add(list.getData().get(i));
            }
        }
    }

    /*@Override
    public void processFinish(ReturnResult output) {

        TaskList result = (TaskList)output;
        try{
            System.out.println("ddddddddddddddd=" + result.getData().size());
            loadData((TaskList) output);
            tasksAdapter.notifyDataSetChanged();
            System.out.println("changed");
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }*/

    /*@Override
    public void processFinish(TaskList output) {
        loadData(output);
        tasksAdapter.notifyDataSetChanged();
    }*/

    /*public class TaskListDataTask extends AsyncTask<Void, Void, TaskList> {
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
    }*/
}
