package com.just.sun.activitys;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.just.sun.R;
import com.just.sun.widget.danmu.AnimationHelper;
import com.just.sun.widget.danmu.ScreenUtils;
import com.just.sun.widget.danmu.TanmuBean;

import java.lang.ref.WeakReference;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by walkingMen on 2016/5/9.
 */
public class DanmuActivity extends Activity {
    private int danmuFlag = 0;

    private TanmuBean tanmuBean;

    private Queue<String> danmakuQueue = new LinkedList<>();
    private MyHandler handler;

    //放置弹幕内容的父组件
    private RelativeLayout tanmu_containerA, tanmu_containerB, tanmu_containerC;
    private LinearLayout containerVG;

    private Button addTanmu;

    private int danmuNum = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danmu);

        containerVG = (LinearLayout) findViewById(R.id.containerVG);
        tanmu_containerA = (RelativeLayout) findViewById(R.id.tanmu_containerA);
        tanmu_containerB = (RelativeLayout) findViewById(R.id.tanmu_containerB);
        tanmu_containerC = (RelativeLayout) findViewById(R.id.tanmu_containerC);
        addTanmu = (Button) findViewById(R.id.addTanmu);
        handler = new MyHandler(this);

        addTanmu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendTanmu(danmuNum++ + "<----弹幕弹幕--->");
            }
        });
    }

    private void sendTanmu(String s) {
        danmakuQueue.offer(s);
        tanmuBean = new TanmuBean();
        tanmuBean.setItems(new String[]{s});
        CreateTanmuThread mCreateTanmuThread = new CreateTanmuThread();
        new Thread(mCreateTanmuThread).start();

    }

    //自动添加弹幕
    private class CreateTanmuThread implements Runnable {
        @Override
        public void run() {
            int N = tanmuBean.getItems().length;
            for (int i = 0; i < N; i++) {
                handler.obtainMessage(1, i, 0).sendToTarget();
                SystemClock.sleep(2000);
            }
        }
    }

    //需要在主线城中添加组件
    private static class MyHandler extends Handler {
        private WeakReference<DanmuActivity> ref;

        MyHandler(DanmuActivity ac) {
            ref = new WeakReference<>(ac);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if (msg.what == 1) {
                DanmuActivity ac = ref.get();
                if (ac != null && ac.tanmuBean != null) {
                    int index = msg.arg1;
                    String content = ac.tanmuBean.getItems()[index];
                    ac.showTanmu(content);
                }
            }
        }
    }

    private void showTanmu(String content) {
        final View view = View.inflate(DanmuActivity.this, R.layout.item_live_danmu, null);
        TextView contentView = (TextView) view.findViewById(R.id.content);
        contentView.setText(content);
        view.measure(-1, -1);
        int measuredWidth = view.getMeasuredWidth();
        int leftMargin = ScreenUtils.getScreenW(this);
        int rightMargin = ScreenUtils.getScreenW(this) + measuredWidth;
        Log.i("SHF", "leftMargin-->" + leftMargin);
        Log.i("SHF", "rightMargin-->" + rightMargin);
        Log.i("SHF", "getScreenW-->" + ScreenUtils.getScreenW(this));

        if (danmuFlag == 0) {
            danmuFlag = 1;
            Animation anim = AnimationHelper.createTranslateAnim(this, leftMargin, rightMargin);
            anim.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    danmakuQueue.poll();
                    if (danmakuQueue.size() == 0) {
                        danmuFlag = 0;
                    }
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }
            });
            view.startAnimation(anim);
            tanmu_containerA.addView(view);
        } else if (danmuFlag == 1) {
            danmuFlag = 2;
            Animation anim = AnimationHelper.createTranslateAnim(this, leftMargin, -rightMargin);
            anim.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                }

                @Override
                public void onAnimationEnd(Animation animation) {

                    danmakuQueue.poll();
                    if (danmakuQueue.size() == 0) {
                        danmuFlag = 0;
                    }
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }
            });
            view.startAnimation(anim);
            tanmu_containerB.addView(view);
        } else if (danmuFlag == 2) {
            danmuFlag = 0;
            Animation anim = AnimationHelper.createTranslateAnim(this, leftMargin, -rightMargin);
            anim.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    danmakuQueue.poll();
                    if (danmakuQueue.size() == 0) {
                        danmuFlag = 0;
                    }
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }
            });
            view.startAnimation(anim);
            tanmu_containerC.addView(view);
        }

    }

}
