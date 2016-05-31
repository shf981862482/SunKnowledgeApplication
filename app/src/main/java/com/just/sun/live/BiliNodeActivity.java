package com.just.sun.live;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.just.sun.R;

public class BiliNodeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bili_node);
        BiliNodeActivity.this.getWindow().getDecorView()
                .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE);
    }
}
