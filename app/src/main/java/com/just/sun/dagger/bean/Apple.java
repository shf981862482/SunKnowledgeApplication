package com.just.sun.dagger.bean;

import javax.inject.Inject;

/**
 * Created by walkingMen on 2016/6/14.
 */
public class Apple extends Fruit {
    public Apple() {
        super();
    }
    @Inject
    public Apple(String name){
        super(name);
    }

}
