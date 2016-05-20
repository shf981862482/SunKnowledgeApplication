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
        fragmentList.add(HotFragment.newInstance("难得糊涂", null));
        fragmentList.add(HotFragment.newInstance("明镜非台", null));
        fragmentList.add(HotFragment.newInstance("一花一世界", null));
        fragmentList.add(HotFragment.newInstance("一树一菩提", null));
        fragmentList.add(HotFragment.newInstance("云卷云舒", null));

        titles.add("难得糊涂");
        titles.add("明镜非台");
        titles.add("一花一世界");
        titles.add("一树一菩提");
        titles.add("云卷云舒");

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
