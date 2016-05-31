package com.just.sun.live;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.common.widget.RoundProgressBar;
import com.just.sun.R;

public class ComboClickActivity extends Activity {

    private RoundProgressBar circularProgress;

    private int rewardNum;
    private TextView txtv_count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combo_click);

        circularProgress = (RoundProgressBar) findViewById(R.id.circularSeekBar);
        txtv_count = (TextView) findViewById(R.id.txtv_count);
        circularProgress.setLianJiListener(lianJiListener);
        circularProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                circularProgress.reStart();
            }
        });

    }


    private RoundProgressBar.LianJiListener lianJiListener = new RoundProgressBar.LianJiListener() {
        @Override
        public void count(int count) {
            rewardNum = count;
            txtv_count.setText("连击：" + rewardNum);
        }

        @Override
        public void complete() {
            circularProgress.clear();
        }
    };
}
