package com.example.junlianglin.learningone.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.example.junlianglin.framework.fragment.BaseFragment;
import com.example.junlianglin.learningone.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;


@ContentView(R.layout.fragment_home)
public class HomeFragment extends BaseFragment {

    @ViewInject(R.id.textViewNews)
    private TextView textViewNews;

    @ViewInject(R.id.textViewPromos)
    private TextView textViewPromos;

    @ViewInject(R.id.textViewRecoms)
    private TextView textViewRecoms;

    @ViewInject(R.id.viewPagerHome)
    private ViewPager viewPagerHome;

    private List<Fragment> fragmentList = new ArrayList<>();

    private HomeNewsFragment homeNewsFragment;
    private HomePromsFragment homePromsFragment;
    private HomeRecomsFragment homeRecomsFragment;



    @Override
    protected void initParams() {
        homeNewsFragment = new HomeNewsFragment();
        homePromsFragment = new HomePromsFragment();
        homeRecomsFragment = new HomeRecomsFragment();
        fragmentList.add(homeNewsFragment);
        fragmentList.add(homePromsFragment);
        fragmentList.add(homeRecomsFragment);

        viewPagerHome.setAdapter(new FragmentStatePagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }
        });
        viewPagerHome.setCurrentItem(0);
        viewPagerHome.addOnPageChangeListener(new ViewPageOnChangeListener());



    }


    // must be define as a private method
    @Event(value = {R.id.textViewNews,R.id.textViewPromos,R.id.textViewRecoms})
    private void onTextViewClick(View view){
        System.out.println(view.getId());

        switch (view.getId()){

            case  R.id.textViewNews:
                viewPagerHome.setCurrentItem(0);
                break;
            case R.id.textViewPromos:

                viewPagerHome.setCurrentItem(1);
                break;

            case R.id.textViewRecoms:
                viewPagerHome.setCurrentItem(2);
                break;
            default:
                viewPagerHome.setCurrentItem(0);
                break;
        }


    }
    @Event(value = R.id.viewPagerHome,
            type = ViewPager.OnPageChangeListener.class)
    private void onViewPageOnChange(){

    }

    public class ViewPageOnChangeListener implements ViewPager.OnPageChangeListener{
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            System.out.println("position=" + position);
            switch (position){
                case 0:
                    textViewNews.setTextColor(getResources().getColor(R.color.colorAccent));
                    textViewPromos.setTextColor(getResources().getColor(R.color.colorPrimary));
                    textViewRecoms.setTextColor(getResources().getColor(R.color.colorPrimary));
                    break;
                case 1:
                    textViewNews.setTextColor(getResources().getColor(R.color.colorPrimary));
                    textViewPromos.setTextColor(getResources().getColor(R.color.colorAccent));
                    textViewRecoms.setTextColor(getResources().getColor(R.color.colorPrimary));
                    break;
                case 2:
                    textViewNews.setTextColor(getResources().getColor(R.color.colorPrimary));
                    textViewPromos.setTextColor(getResources().getColor(R.color.colorPrimary));
                    textViewRecoms.setTextColor(getResources().getColor(R.color.colorAccent));
                    break;
                default:
                    break;

            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
}
