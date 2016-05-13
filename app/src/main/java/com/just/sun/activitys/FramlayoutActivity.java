package com.just.sun.activitys;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.just.sun.R;

public class FramlayoutActivity extends Activity {
    FrameLayout fram;
    boolean show = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_framlayout);
        fram = (FrameLayout) findViewById(R.id.fram02);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (show) {
                    fram.setVisibility(View.GONE);
                } else {
                    fram.setVisibility(View.VISIBLE);
                }
                show = !show;
            }
        });
    }
}
