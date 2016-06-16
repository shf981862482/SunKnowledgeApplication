package com.common.base;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.common.application.SunApplication;
import com.just.sun.dagger.component.ApplicationComponent;

/**
 * Created by walkingMen on 2016/5/31.
 */
public class BaseFragmentActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getApplicationComponent().inject(this);//调用Component的实现类将Module的生成的对象注入其中
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
    }

    protected ApplicationComponent getApplicationComponent() {
        return ((SunApplication) getApplication()).getApplicationComponent();
    }
}
