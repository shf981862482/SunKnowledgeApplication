package com.common.webview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.common.utils.ActivityUtils;

/**
 * Created by walkingMen on 2016/5/31.
 */
public class WebViewUIManager {
    /**
     * 打开普通H5页面
     *
     * @param context
     * @param url
     */
    public static void openWebViewPage(Context context, String url) {
        Bundle bundle = new Bundle();
        bundle.putString(SunBrowserActivity.EXTRA_INITURL, url);
        Intent intent = new Intent(context, SunBrowserActivity.class);
        intent.putExtras(bundle);
        context.startActivity(intent);
//        ActivityUtils.toActivity(context, SunBrowserActivity.class, bundle);
    }
}
