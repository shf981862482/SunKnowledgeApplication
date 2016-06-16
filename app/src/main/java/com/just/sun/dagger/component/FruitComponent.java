package com.just.sun.dagger.component;

import com.just.sun.dagger.bean.Fruit;
import com.just.sun.dagger.module.FruitModule;

import dagger.Component;

/**
 * Created by walkingMen on 2016/6/14.
 */
@Component(modules = {FruitModule.class})
public interface FruitComponent {
//    void inject(FruitActivity activity);
    Fruit fruit();
}
