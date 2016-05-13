package com.just.sun.activitys;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.just.sun.R;
import com.just.sun.widget.LineLoadingProgressBar;

public class LoadingDialogActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_dialog);
        final LineLoadingProgressBar lineLoadingProgressBar = new LineLoadingProgressBar(LoadingDialogActivity.this);
        findViewById(R.id.btn_show).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lineLoadingProgressBar.show();
            }
        });
        findViewById(R.id.btn_dismiss).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lineLoadingProgressBar.dismiss();
            }
        });
    }
}
