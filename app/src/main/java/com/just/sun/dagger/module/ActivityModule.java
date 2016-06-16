
package com.just.sun.dagger.module;

import android.app.Activity;

import com.common.base.BaseFragmentActivity;
import com.just.sun.dagger.defined.PerActivity;

import dagger.Module;
import dagger.Provides;


/**
 * Created by walkingMen on 2016/6/8.
 */

@Module
public class ActivityModule {
    private final BaseFragmentActivity activity;

    public ActivityModule(BaseFragmentActivity activity) {
        this.activity = activity;
    }

    @Provides
    @PerActivity
    Activity activity() {
        return this.activity;
    }

    @Provides
    FruitModule provideFruitModel(){
        return new FruitModule();
    }

}

