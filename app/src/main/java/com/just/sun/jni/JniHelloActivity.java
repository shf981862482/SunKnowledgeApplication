package com.just.sun.jni;

import android.os.Bundle;
import android.app.Activity;
import android.widget.TextView;

import com.just.sun.R;

public class JniHelloActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jni_hello);
        TextView txtView = (TextView) findViewById(R.id.txt);
        NdkJniUtils jni = new NdkJniUtils();
        txtView.setText(jni.getCLanguageString());
    }
}
