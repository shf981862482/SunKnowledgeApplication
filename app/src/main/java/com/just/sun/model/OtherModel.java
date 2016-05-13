package com.just.sun.model;

import android.databinding.ObservableArrayList;

import com.common.bindingcollectionadapter.ItemView;
import com.just.sun.BR;
import com.just.sun.R;


/**
 * Created by walkingMen on 2016/3/29.
 */
public class OtherModel {
    public ObservableArrayList<SimpleModel> items = new ObservableArrayList<>();
    public final ItemView itemView = ItemView.of(BR.simpleModel, R.layout.item_single_dbing);

    public OtherModel() {
    }
}