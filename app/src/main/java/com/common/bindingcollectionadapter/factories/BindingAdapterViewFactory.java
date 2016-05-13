package com.common.bindingcollectionadapter.factories;

import android.widget.AdapterView;

import com.common.bindingcollectionadapter.BindingListViewAdapter;
import com.common.bindingcollectionadapter.ItemViewArg;


public interface BindingAdapterViewFactory {

    public <T> BindingListViewAdapter<T> create(AdapterView adapterView, ItemViewArg<T> arg);

    public BindingAdapterViewFactory DEFAULT = new BindingAdapterViewFactory() {
        @Override
        public <T> BindingListViewAdapter<T> create(AdapterView adapterView, ItemViewArg<T> arg) {
            return new BindingListViewAdapter<>(arg);
        }
    };

}
