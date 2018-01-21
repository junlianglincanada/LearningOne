package com.example.junlianglin.learningone.activity;


import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import android.os.Bundle;

import android.view.MenuItem;


import com.example.junlianglin.learningone.fragment.DashboardFragment;
import com.example.junlianglin.learningone.fragment.HomeFragment;
import com.example.junlianglin.learningone.fragment.NotificationsFragment;
import com.example.junlianglin.framework.activity.BaseActivity;
import com.example.junlianglin.framework.application.MyApplication;
import com.example.junlianglin.learningone.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

@ContentView(R.layout.activity_start)
public class StartActivity extends BaseActivity {



    @ViewInject(R.id.navigation)
    private BottomNavigationView navigation;

    private FragmentTransaction fragmentTransaction;

    private HomeFragment homeFragment;
    private DashboardFragment dashboardFragment;
    private NotificationsFragment notificationsFragment;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //x.view().inject(this);
        //MyApplication myApplication = (MyApplication)getApplicationContext();
        if (savedInstanceState==null)
            navigation.setSelectedItemId(R.id.navigation_home);
        //setContentView(R.layout.activity_start);
    }


    @Event(value = R.id.navigation,
            type = BottomNavigationView.OnNavigationItemSelectedListener.class)
    private boolean onNavigationItemClick(MenuItem item) {
        System.out.println("item.getItemId()=" +item.getItemId() + " " + item.getTitle());
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        boolean clickStatus = false;
        try {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    //textView.setText(R.string.title_home);
                   /* getSupportFragmentManager().beginTransaction()
                            .add(R.id.frameLayout_MainFrame, new HomeFragment(), null)
                            .commit();*/
                    /*if (homeFragment==null){
                        homeFragment = new HomeFragment();
                    }
                    fragmentTransaction.replace(R.id.frameLayout_MainFrame,homeFragment);*/
                    fragmentTransaction.replace(R.id.frameLayout_MainFrame,
                           HomeFragment.instantiate(StartActivity.this,HomeFragment.class.getName(),null),"home");


                    clickStatus =  true;
                    break;
                case R.id.navigation_dashboard:

                    /*getSupportFragmentManager().beginTransaction()
                            .add(R.id.frameLayout_MainFrame, new DashboardFragment(), null)
                            .commit();*/
                    /*if (dashboardFragment==null){
                        dashboardFragment = new DashboardFragment();
                    }
                    fragmentTransaction.replace(R.id.frameLayout_MainFrame,dashboardFragment);*/
                    fragmentTransaction.replace(R.id.frameLayout_MainFrame,
                            DashboardFragment.instantiate(StartActivity.this,DashboardFragment.class.getName(),null),"dashboard");

                    clickStatus =  true;
                    break;
                case R.id.navigation_notifications:
                    getSupportFragmentManager().beginTransaction()
                            .add(R.id.frameLayout_MainFrame, new NotificationsFragment(), null);
                    /*if (notificationsFragment==null){
                        notificationsFragment = new NotificationsFragment();
                    }
                    fragmentTransaction.replace(R.id.frameLayout_MainFrame,notificationsFragment);*/
                   fragmentTransaction.replace(R.id.frameLayout_MainFrame,
                            NotificationsFragment.instantiate(StartActivity.this,NotificationsFragment.class.getName(),null),"notifications");

                    clickStatus =  true;
                    break;
                default:
                    //textView.setText("adfasdf");
                    break;

            }

        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        fragmentTransaction.commit();
        System.out.println("fragmentTransaction.commit()");
        System.out.println(getSupportFragmentManager().getFragments().size());
        return clickStatus;
    }

    private void hideFragment(FragmentTransaction fragmentTransaction, Fragment fragment){

    }




    @Override
    protected void onResume() {
        super.onResume();
    }

    //@Override
    //protected int getLayoutId() {
        //return R.layout.activity_start;
    //}

    @Override
    protected void initParams() {

    }
}
