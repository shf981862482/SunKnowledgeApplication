package com.common.bindingcollectionadapter.factories;

import android.support.v4.view.ViewPager;

import com.common.bindingcollectionadapter.BindingViewPagerAdapter;
import com.common.bindingcollectionadapter.ItemViewArg;


public interface BindingViewPagerAdapterFactory {
    <T> BindingViewPagerAdapter<T> create(ViewPager viewPager, ItemViewArg<T> arg);

    BindingViewPagerAdapterFactory DEFAULT = new BindingViewPagerAdapterFactory() {
        @Override
        public <T> BindingViewPagerAdapter<T> create(ViewPager viewPager, ItemViewArg<T> arg) {
            return new BindingViewPagerAdapter<>(arg);
        }
    };

}
