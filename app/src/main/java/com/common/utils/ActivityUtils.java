package com.common.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by walkingMen on 2016/5/31.
 */
public class ActivityUtils {
    public static void toActivity(Context context, Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(context, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }
}
