package com.example.junlianglin.learningone.activity;


import android.content.Intent;
import android.content.IntentFilter;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import android.os.Bundle;

import android.view.MenuItem;


import com.example.junlianglin.learningone.fragment.DashboardFragment;
import com.example.junlianglin.learningone.fragment.HomeFragment;
import com.example.junlianglin.learningone.fragment.MoreFragment;
import com.example.junlianglin.learningone.fragment.NotificationsFragment;
import com.example.junlianglin.framework.activity.BaseActivity;
import com.example.junlianglin.learningone.R;
import com.example.junlianglin.learningone.model.TaskList;
import com.example.junlianglin.learningone.service.TaskService;
import com.example.junlianglin.learningone.utils.NetworkChangeReceiver;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_start)
public class StartActivity extends BaseActivity {


    private IntentFilter intentFilter;
    private NetworkChangeReceiver networkChangeReceiver;

    @ViewInject(R.id.navigation)
    private BottomNavigationView navigation;

    private FragmentTransaction fragmentTransaction;

    private HomeFragment homeFragment = new HomeFragment();
    private DashboardFragment dashboardFragment = new DashboardFragment();
    private NotificationsFragment notificationsFragment = new NotificationsFragment();
    private  MoreFragment moreFragment = new MoreFragment();


    private String token = "";

    private Fragment currentFragment=new Fragment();;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //x.view().inject(this);
        //MyApplication myApplication = (MyApplication)getApplicationContext();

        intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        networkChangeReceiver = new NetworkChangeReceiver();
        registerReceiver(networkChangeReceiver, intentFilter);

        Intent intent=getIntent();
        token = intent.getStringExtra("token");

        System.out.println("StartActivity token=" + token);
        switchFragment(homeFragment).commit();









    }





    @Event(value = R.id.navigation,
            type = BottomNavigationView.OnNavigationItemSelectedListener.class)
    private boolean onNavigationItemClick(MenuItem item) {

        boolean clickStatus = false;

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    switchFragment(homeFragment).commit();
                    return true;
                case R.id.navigation_dashboard:
                    switchFragment(dashboardFragment).commit();
                    return true;
                case R.id.navigation_notifications:
                    switchFragment(notificationsFragment).commit();
                    return true;
                case R.id.navigation_more:
                    switchFragment(moreFragment).commit();
                    return true;
                default:
                    break;
            }


        return false;
    }



    @Override
    protected void onResume() {

        super.onResume();
    }

    @Override
    protected  void onDestroy(){
        super.onDestroy();
        unregisterReceiver(networkChangeReceiver);
    }

    @Override
    protected  void onStop(){
        super.onStop();

    }

    //@Override
    //protected int getLayoutId() {
        //return R.layout.activity_start;
    //}



    @Override
    protected void initParams() {
        //TaskService service = new TaskService();
        //service.execute((Void) null);



    }


    private FragmentTransaction switchFragment(Fragment targetFragment) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (!targetFragment.isAdded()) {
            if (currentFragment != null) {
                transaction.hide(currentFragment);
            }
            transaction.add(R.id.frameLayout_MainFrame, targetFragment,targetFragment.getClass().getName());

        } else {
            transaction.hide(currentFragment).show(targetFragment);
        }
        currentFragment = targetFragment;
        return transaction;
    }
}
