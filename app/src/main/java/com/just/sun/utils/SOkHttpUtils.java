package com.just.sun.utils;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by pc-004 on 2016/2/26.
 */
public class SOkHttpUtils {
    public static void SGetOkHttp(String urlStr){
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(urlStr)
                .build();

        try {
            okHttpClient.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
