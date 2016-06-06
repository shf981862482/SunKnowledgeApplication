package com.just.sun.multiwindow;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.common.base.BasePresenter;
import com.just.sun.R;
import com.just.sun.multiwindow.activity.SecondMultiActivity;
import com.just.sun.multiwindow.contract.MultiContract;
import com.just.sun.multiwindow.present.MultiPresent;

public class MultiWindowActivity extends Activity implements MultiContract.View {
    private MultiContract.Presenter presenter;
    private TextView tview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_window);
        tview = (TextView) findViewById(R.id.txt_content);
        findViewById(R.id.img_beauty).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTxt("点击分屏了");
                presenter.saveContent(tview.getText().toString());
                Intent intent = new Intent(MultiWindowActivity.this, SecondMultiActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_LAUNCH_ADJACENT);
                startActivity(intent);
            }
        });

        new MultiPresent(this);
    }

    @Override
    public void showTxt(String content) {
        tview.setText(content);
    }

    @Override
    public void hideTxt() {

    }

    @Override
    public void setPresenter(MultiContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
