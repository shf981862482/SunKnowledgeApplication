package com.common.application;

import android.app.Application;

import com.common.common.Global;

/**
 * Created by walkingMen on 2016/5/31.
 */
public class SunApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Global.mContext = SunApplication.this.getApplicationContext();
    }
}
