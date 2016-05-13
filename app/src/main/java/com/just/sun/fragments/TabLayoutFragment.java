package com.just.sun.fragments;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.just.sun.R;
import com.just.sun.adapters.TabPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class TabLayoutFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private List<Fragment> fragmentList;
    private List<String> titles;


    private TabLayout tabLayout;
    private ViewPager viewPager;

    private TabPagerAdapter tAdapter;


    public TabLayoutFragment() {
    }


    public static TabLayoutFragment newInstance(String param1, String param2) {
        TabLayoutFragment fragment = new TabLayoutFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_layout, container, false);
        init(view);


        return view;
    }

    private void init(View view) {
        tabLayout = (TabLayout) view.findViewById(R.id.tab);
        viewPager = (ViewPager) view.findViewById(R.id.vp);

        fragmentList = new ArrayList<>();
        titles = new ArrayList<>();
        fragmentList.add(HotFragment.newInstance("热门", null));
        fragmentList.add(HotFragment.newInstance("特惠", null));
        fragmentList.add(HotFragment.newInstance("门票", null));
        fragmentList.add(HotFragment.newInstance("线路", null));
        fragmentList.add(HotFragment.newInstance("国际", null));

        titles.add("热门");
        titles.add("特惠");
        titles.add("门票");
        titles.add("线路");
        titles.add("国际");

        tabLayout.setTabMode(TabLayout.MODE_FIXED);//设置TabLayout的模式
        //        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);//设置TabLayout的模式


        //为TabLayout添加tab名称后这是icon
        tabLayout.addTab(tabLayout.newTab().setText(titles.get(0)));
        tabLayout.addTab(tabLayout.newTab().setText(titles.get(1)));
        tabLayout.addTab(tabLayout.newTab().setText(titles.get(2)));
        tabLayout.addTab(tabLayout.newTab().setText(titles.get(3)));
        tabLayout.addTab(tabLayout.newTab().setText(titles.get(4)));

        tAdapter = new TabPagerAdapter(getFragmentManager(), fragmentList, titles);
        viewPager.setAdapter(tAdapter);
        viewPager.setCurrentItem(0);

        tabLayout.setupWithViewPager(viewPager);

    }

}
