package com.just.sun.activitys;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.just.sun.R;
import com.just.sun.widget.danmu.DanmuBase.DanmakuActionManager;
import com.just.sun.widget.danmu.DanmuBase.DanmakuChannel;
import com.just.sun.widget.danmu.DanmuBase.DanmakuEntity;

/**
 * Created by walkingMen on 2016/5/9.
 */
public class SunDanmuActivity extends Activity {
    DanmakuChannel danA, danB, danC;
    DanmakuActionManager danmakuActionManager;

    private int count;
    private Button addTanmu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danmu_sun);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
        danA = (DanmakuChannel) findViewById(R.id.danA);
        danB = (DanmakuChannel) findViewById(R.id.danB);
        danC = (DanmakuChannel) findViewById(R.id.danC);
         addTanmu = (Button) findViewById(R.id.addTanmu);
        danmakuActionManager = new DanmakuActionManager();
        danA.setDanAction(danmakuActionManager);
        danB.setDanAction(danmakuActionManager);
        danC.setDanAction(danmakuActionManager);
        danmakuActionManager.addChannel(danA);
        danmakuActionManager.addChannel(danB);
        danmakuActionManager.addChannel(danC);


        addTanmu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                danmakuActionManager.addDanmu(new DanmakuEntity(count++ + "弹幕  come on-->"));
            }
        });
    }

}
