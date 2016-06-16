package com.just.sun.dagger.bean;

import javax.inject.Inject;

/**
 * Created by walkingMen on 2016/6/14.
 */
public class Fruit {
    private String name;

    public Fruit() {
    }

    @Inject
    public Fruit(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
