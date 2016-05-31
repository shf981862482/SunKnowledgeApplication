package com.just.sun.gpulutimage.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


import java.util.List;

/**
 * Created by walkingMen on 2016/5/24.
 */
public class ImgFragPageAdapter extends FragmentPagerAdapter {
    List<Fragment> list;

    public ImgFragPageAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.list = list;

    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }
}
