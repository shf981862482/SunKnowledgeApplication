package com.just.sun.dagger.module;

import com.just.sun.dagger.bean.ShoppingCarModel;

import dagger.Module;
import dagger.Provides;

/**
 * Created by walkingMen on 2016/6/15.
 */
@Module
public class ContainerModule {
    @Provides
    ShoppingCarModel provideShoppingCar(){
        return new ShoppingCarModel(521);
    }
}
