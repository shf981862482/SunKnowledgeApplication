
package com.just.sun.dagger.module;

import android.content.Context;

import com.common.application.SunApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


/**
 * Created by walkingMen on 2016/6/8.
 */

@Module
public class ApplicationModule {
    private final SunApplication application;
    public ApplicationModule(SunApplication application) {
        this.application = application;
    }

    @Provides @Singleton
    Context provideSunApplication(){
        return this.application;
    }
}

