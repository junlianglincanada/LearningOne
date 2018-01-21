package com.example.junlianglin.learningone.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.junlianglin.framework.fragment.BaseFragment;
import com.example.junlianglin.learningone.R;
import com.example.junlianglin.learningone.adapter.PromosAdapter;
import com.example.junlianglin.learningone.model.Promos;

import org.xutils.view.annotation.ContentView;

import java.util.ArrayList;
import java.util.List;


@ContentView(R.layout.fragment_home_promos)
public class HomePromsFragment extends BaseFragment   {
    //private RecyclerView recyclerView;
    //private PromosAdapter promosAdapter;

    public HomePromsFragment() {
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

        /*recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);

        //initData();
        List<Promos> promosList = new ArrayList<Promos>();
        promosAdapter = new PromosAdapter(promosList);
        promosAdapter.setOnRecyclerViewListener(null);
        recyclerView.setAdapter(promosAdapter);*/

    }
}
