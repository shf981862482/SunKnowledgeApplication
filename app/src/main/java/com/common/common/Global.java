package com.common.common;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.view.WindowManager;

import java.lang.reflect.Field;

/**
 * Created by 弘法 on 2016/1/22.
 */
public class Global {

    private static final String TAG = "Global";

    private static int mScreenWidth;

    private static int mScreenHeight;
    public static int mTLPicWidth;
    public static int mTLPicHeight;
    public static int contentTop;//状态栏高

    private static Looper mLooper;
    private static Handler mWorkHandler;
    private static Handler uiHandler;
    //application上下文
    public static Context mContext;
    public static boolean isPageFont = false;//为true在前台

    static {
        HandlerThread mThread = new HandlerThread("Global");
        mThread.start();
        mLooper = mThread.getLooper();
        mWorkHandler = new Handler(mLooper);
        //init uiHandler
        uiHandler = new Handler(Looper.getMainLooper());
    }

    /**
     * 全局的一些变量的初始化
     */
    public static void init(Activity activity) {
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        mScreenWidth = wm.getDefaultDisplay().getWidth();
        mScreenHeight = wm.getDefaultDisplay().getHeight();
        mTLPicWidth = mScreenWidth;
        mTLPicHeight = mScreenWidth;
    }

    public static int contentTop(Activity activity) {
        if (0 == contentTop) {
            try {
                Class c = Class.forName("com.android.internal.R$dimen");
                Object obj = c.newInstance();
                Field field = c.getField("status_bar_height");
                int x = Integer.parseInt(field.get(obj).toString());
                contentTop = activity.getResources().getDimensionPixelSize(x);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
        return contentTop;
    }

    /***
     * 获取全局变量
     *
     * @return
     */
    public static Context getContext() {
        return mContext;
    }

    /***
     * 非UI线程调用耗时操作
     *
     * @param r
     */
    public static final void post2work(Runnable r) {
        mWorkHandler.post(r);
    }

    public static final void post2workDelay(Runnable r, long time) {
        mWorkHandler.postDelayed(r, time);
    }

    /***
     * Runnable post到主线程
     *
     * @param r
     */
    public static final void post2UI(Runnable r) {
        uiHandler.post(r);
    }

    public static final void postDelay2UI(Runnable r, long time) {
        uiHandler.postDelayed(r, time);
    }

    public static void removeRunnale(Runnable r) {
        uiHandler.removeCallbacks(r);
        mWorkHandler.removeCallbacks(r);
    }

    public static int getScreenWidth() {
        if (0 == mScreenWidth) {
            WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
            mScreenWidth = wm.getDefaultDisplay().getWidth();
        }
        return mScreenWidth;
    }

    public static int getScreenHeight() {
        if (0 == mScreenHeight) {
            WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
            mScreenHeight = wm.getDefaultDisplay().getHeight();
        }
        return mScreenHeight;
    }

    /**
     * 获取状态栏的高度
     *
     * @return
     */
    public static int getStatusBarHeight() {
        int result = 0;
        int resourceId = mContext.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = mContext.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public static Looper getWorkThreadLooper() {
        return mLooper;
    }

}
