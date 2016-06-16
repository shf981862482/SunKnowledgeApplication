package com.just.sun.dagger.module;


import com.just.sun.dagger.bean.Apple;
import com.just.sun.dagger.bean.Fruit;

import dagger.Module;
import dagger.Provides;

/**
 * Created by walkingMen on 2016/6/14.
 */
@Module
public class FruitModule {
    @Provides
    public Fruit provideFruit() {
        return new Apple("苹果");
    }

}
