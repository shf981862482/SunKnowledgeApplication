package com.just.sun.live;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;

import com.just.sun.R;

import io.vov.vitamio.utils.Log;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;
import tv.danmaku.ijk.media.widget.media.AndroidMediaController;
import tv.danmaku.ijk.media.widget.media.IjkVideoView;


public class BiliPlayerActivity extends Activity {
    private final String TAG = "BiliPlayerActivity";
    private IjkVideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bili_player);
        Log.i("SHF", "BiliPlayerActivity");
        IjkMediaPlayer.loadLibrariesOnce(null);
        IjkMediaPlayer.native_profileBegin("libijkplayer.so");
        videoView = (IjkVideoView) findViewById(R.id.ijkPlayer);
        //  videoView.setBackgroundColor(Color.parseColor("#F6F6F6"));
        //  videoView.setOnErrorListener(new ErrorListener());
        AndroidMediaController controller = new AndroidMediaController(this, false);
        videoView.setMediaController(controller);
        String playUrl = SharedPreUtil.getString(this, "playUrl");
//        String url = "http://devimages.apple.com.edgekey.net/streaming/examples/bipbop_4x3/gear3/prog_index.m3u8";
        videoView.setVideoURI(Uri.parse(playUrl));

        videoView.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        videoView.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        videoView.resume();
    }


}
