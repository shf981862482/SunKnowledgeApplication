package com.just.sun.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.just.sun.BR;

/**
 * Created by walkingMen on 2016/3/21.
 */
public class SimpleModel extends BaseObservable {
    private String content;

    public SimpleModel(String content) {
        this.content = content;
    }

    @Bindable
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
        notifyPropertyChanged(BR.content);
    }
}
