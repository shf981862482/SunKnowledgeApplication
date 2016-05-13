package com.rongs.hanj.apptanmudemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Set;

/**
 * 简易弹幕效果实现
 * Created by hanj on 15-6-4.
 */
public class MainActivity extends ActionBarActivity {
    private MyHandler handler;

    //弹幕内容
    private TanmuBean tanmuBean;
    //放置弹幕内容的父组件
    private RelativeLayout containerVG;

    //父组件的高度
    private int validHeightSpace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        containerVG = (RelativeLayout) findViewById(R.id.tanmu_container);
        tanmuBean = new TanmuBean();
        tanmuBean.setItems(new String[]{"测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下测试一下", "弹幕这东西真不好做啊", "总是出现各种问题~~", "也不知道都是为什么？麻烦！", "哪位大神可以帮帮我啊？", "I need your help.",
                "测试一下", "弹幕这东西真不好做啊弹幕这东西真不好做啊弹幕这东西真不好做啊弹幕这东西真不好做啊", "总是出现各种问题~~", "也不知道都是为什么也不知道都是为什么也不知道都是为什么也不知道都是为什么？麻烦！", "哪位大神可以帮帮我啊哪位大神可以帮帮我啊哪位大神可以帮帮我啊哪位大神可以帮帮我啊？", "I need your help.",
                "测试一下", "弹幕这东西真不好做啊", "总是出现各种问题总是出现各种问题总是出现各种问题~~", "也不知道都是为什么？麻烦！", "哪位大神可以帮帮我啊哪位大神可以帮帮我啊哪位大神可以帮帮我啊哪位大神可以帮帮我啊哪位大神可以帮帮我啊？", "I need your help."});

        handler = new MyHandler(this);

        //开始弹幕
        View startTanmuView = findViewById(R.id.startTanmu);
        startTanmuView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (containerVG.getChildCount() > 0) {
                    return;
                }

                existMarginValues.clear();
                new Thread(new CreateTanmuThread()).start();
            }
        });
    }

    //每2s自动添加一条弹幕
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
        private WeakReference<MainActivity> ref;

        MyHandler(MainActivity ac) {
            ref = new WeakReference<>(ac);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if (msg.what == 1) {
                MainActivity ac = ref.get();
                if (ac != null && ac.tanmuBean != null) {
                    int index = msg.arg1;
                    String content = ac.tanmuBean.getItems()[index];
                    float textSize = (float) (ac.tanmuBean.getMinTextSize() * (1 + Math.random() * ac.tanmuBean.getRange()));
                    int textColor = ac.tanmuBean.getColor();

                    ac.showTanmu(content, textSize, textColor);
                }
            }
        }
    }

    private void showTanmu(String content, float textSize, int textColor) {
        final TextView textView = new TextView(this);

        textView.setTextSize(textSize);
        textView.setText(content);
//        textView.setSingleLine();
        textView.setTextColor(textColor);

        int leftMargin = containerVG.getRight() - containerVG.getLeft() - containerVG.getPaddingLeft();
        //计算本条弹幕的topMargin(随机值，但是与屏幕中已有的不重复)
        int verticalMargin = getRandomTopMargin();
        textView.setTag(verticalMargin);

        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        params.topMargin = verticalMargin;
        textView.setLayoutParams(params);
        textView.setGravity(Gravity.CENTER_HORIZONTAL);

        Animation anim = AnimationHelper.createTranslateAnim(this, leftMargin, -ScreenUtils.getScreenW(this));
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //移除该组件
                containerVG.removeView(textView);
                //移除占位
                int verticalMargin = (int) textView.getTag();
                existMarginValues.remove(verticalMargin);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        textView.startAnimation(anim);

        containerVG.addView(textView);
    }

    //记录当前仍在显示状态的弹幕的位置（避免重复）
    private Set<Integer> existMarginValues = new HashSet<>();
    private int linesCount;

    private int getRandomTopMargin() {
        //计算用于弹幕显示的空间高度
        if (validHeightSpace == 0) {
            validHeightSpace = containerVG.getBottom() - containerVG.getTop()
                    - containerVG.getPaddingTop() - containerVG.getPaddingBottom();
        }

        //计算可用的行数
        if (linesCount == 0) {
            linesCount = validHeightSpace / ScreenUtils.dp2px(this, tanmuBean.getMinTextSize() * (1 + tanmuBean.getRange()));
            if (linesCount == 0) {
                throw new RuntimeException("Not enough space to show text.");
            }
        }

        //检查重叠
        while (true) {
            int randomIndex = (int) (Math.random() * linesCount);
            int marginValue = randomIndex * (validHeightSpace / linesCount);

            if (!existMarginValues.contains(marginValue)) {
                existMarginValues.add(marginValue);
                return marginValue;
            }
        }
    }
}
