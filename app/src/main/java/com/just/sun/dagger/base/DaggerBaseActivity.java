package com.just.sun.dagger.base;

import android.app.Activity;
import android.os.Bundle;

import com.common.application.SunApplication;
import com.just.sun.dagger.component.ApplicationComponent;

/**
 * Created by walkingMen on 2016/6/13.
 */
public class DaggerBaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        this.getApplicationComponent().inject(this);//调用Component的实现类将Module的生成的对象注入其中
    }

    protected ApplicationComponent getApplicationComponent() {
        return ((SunApplication) getApplication()).getApplicationComponent();
    }
}
