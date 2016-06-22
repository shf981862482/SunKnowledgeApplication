package com.just.sun.activitys;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.just.sun.R;

public class MemoryAnalyzeActivity extends Activity {
    private TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_analyze);
        txt = (TextView) findViewById(R.id.txt);

        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                txt.setText("Done");
            }
        }, 800000L);
    }
}
