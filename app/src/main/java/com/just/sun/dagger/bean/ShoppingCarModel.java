package com.just.sun.dagger.bean;

import javax.inject.Inject;

/**
 * Created by walkingMen on 2016/6/15.
 */
public class ShoppingCarModel {
    private int total;

    public ShoppingCarModel() {
    }

    @Inject
    public ShoppingCarModel(int total) {
        this.total = total;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
