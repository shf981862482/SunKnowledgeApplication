package com.common.bindingcollectionadapter;

import android.databinding.BindingConversion;
import android.databinding.ObservableInt;



/**
 * Created by zhai on 15/11/17.
 */
public class BindingUtils {


    @BindingConversion
    public static String convertIntToStringMode(ObservableInt integer) {
        return String.valueOf(integer.get());
    }




}
