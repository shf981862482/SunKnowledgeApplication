package com.common.application;

import android.app.Application;

import com.common.common.Global;
import com.just.sun.BuildConfig;
import com.just.sun.dagger.component.ApplicationComponent;
import com.just.sun.dagger.component.DaggerApplicationComponent;
import com.just.sun.dagger.module.ApplicationModule;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by walkingMen on 2016/5/31.
 */
public class SunApplication extends Application {
    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        Global.mContext = SunApplication.this.getApplicationContext();
        initializeInjector();
        initializeLeakDetection();
    }

    private void initializeLeakDetection() {
        if (BuildConfig.DEBUG) {
            LeakCanary.install(this);
        }
    }

    private void initializeInjector(){
        this.applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return this.applicationComponent;
    }
}
