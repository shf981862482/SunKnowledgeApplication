package com.just.sun.activitys;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.just.sun.R;

import java.lang.ref.WeakReference;

public class MemoryAnalyzeRightActivity extends Activity {
    private TextView txt;
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_analyze);
        txt = (TextView) findViewById(R.id.txt);

        Handler handler = new Handler();

        handler.postDelayed(new DoneRunnable(txt), 800000L);
    }

    private static final class DoneRunnable implements Runnable {
        private final WeakReference<TextView> txtWeak;

        private DoneRunnable(TextView txtWark) {
            this.txtWeak = new WeakReference<>(txtWark);
        }

        @Override
        public void run() {
            final TextView textView = txtWeak.get();
            if (textView!=null){
                textView.setText("Done");
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
    }
}
