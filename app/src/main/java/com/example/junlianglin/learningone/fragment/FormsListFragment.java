package com.example.junlianglin.learningone.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.junlianglin.framework.fragment.BaseFragment;
import com.example.junlianglin.learningone.R;
import com.example.junlianglin.learningone.adapter.FormAdapter;
import com.example.junlianglin.learningone.model.Form;
import com.example.junlianglin.learningone.model.FormList;
import com.example.junlianglin.learningone.model.ReturnResult;
import com.example.junlianglin.learningone.model.TaskList;
import com.example.junlianglin.learningone.utils.FormAsyncTask;
import com.example.junlianglin.learningone.utils.TaskAsyncTask;
import com.example.junlianglin.learningone.utils.AsyncTaskResponse;
import com.example.junlianglin.learningone.utils.Preferences;
import com.example.junlianglin.learningone.utils.ServerUrl;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;


@ContentView(R.layout.fragment_formlist)
public class FormsListFragment extends BaseFragment   {
    //private RecyclerView recyclerView;
    //private PromosAdapter promosAdapter;

    @ViewInject(R.id.listViewforms)
    private ListView listViewForms;

    private FormAdapter formsAdapter ;

    private List<Form> dataList = new ArrayList<>();

    private FormAsyncTask mAuthTask = null;

    public FormsListFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Nullable
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

        Preferences preferences = new Preferences();
        token = preferences.getAccessToken();
        formsAdapter = new FormAdapter(context,dataList,R.layout.layout_forms);
        listViewForms.setAdapter(formsAdapter);
        String url = ServerUrl.REMOTEURL + ":" + ServerUrl.REMOTEPORT + ServerUrl.FORM_LIST;

        mAuthTask = new FormAsyncTask(token,url,new AsyncTaskResponse(){
            @Override
            public void processFinish(ReturnResult output) {
                loadData((FormList) output);
                formsAdapter.notifyDataSetChanged();
            }
        });
        mAuthTask.execute((Void) null);

    }

    private void loadData(FormList list){
        System.out.println("list.size=" + list.getData().size());
        if (list!=null && list.getData().size()>0){
            for(int i=0;i<list.getData().size();i++){
                dataList.add(list.getData().get(i));
            }
        }
    }
}
