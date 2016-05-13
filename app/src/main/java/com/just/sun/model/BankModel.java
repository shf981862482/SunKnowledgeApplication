package com.just.sun.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.just.sun.BR;

/**
 * Created by walkingMen on 2016/3/18.
 */
public class BankModel extends BaseObservable{
    private String name;

    public BankModel() {
    }

    public BankModel(String name) {
        this.name = name;
    }

    @Bindable
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);

    }
}
