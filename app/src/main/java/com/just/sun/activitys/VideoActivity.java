package com.just.sun.activitys;

import android.os.Bundle;
import android.app.Activity;
import android.support.v7.widget.Toolbar;

import com.just.sun.R;

import io.vov.vitamio.Vitamio;
import io.vov.vitamio.widget.VideoView;

public class VideoActivity extends Activity {
    private VideoView videoView;

    private String path = "http://dlqncdn.miaopai.com/stream/MVaux41A4lkuWloBbGUGaQ__.mp4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        Vitamio.isInitialized(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.dismissPopupMenus();//ToolBar右侧的菜单项消失
//        toolbar.setTitle("首页");

        videoView = (VideoView) findViewById(R.id.vitamio_video);
        videoView.setVideoPath(path);
        videoView.setMediaController(new io.vov.vitamio.widget.MediaController(this));
        videoView.requestFocus();

    }

}
