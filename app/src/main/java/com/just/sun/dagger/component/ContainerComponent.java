package com.just.sun.dagger.component;

import com.just.sun.dagger.activity.FruitActivity;
import com.just.sun.dagger.module.ContainerModule;

import dagger.Component;

/**
 * Created by walkingMen on 2016/6/15.
 */
@Component(dependencies = FruitComponent.class,modules = ContainerModule.class)
public interface ContainerComponent {
    void inject(FruitActivity activity);
}
