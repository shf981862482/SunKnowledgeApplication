package com.just.sun.live;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.just.sun.R;

public class LiveMainActivity extends Activity implements OnClickListener {
	Button playerBtn, encoderBtn;
	EditText playUrl, pubUrl, bufferTime,maxBufferTime;
	CheckBox enablePlayCB, enableVideoCB;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_live_main);
		playerBtn = (Button) findViewById(R.id.button1);
		encoderBtn = (Button) findViewById(R.id.button2);
		playUrl = (EditText) findViewById(R.id.editText_play_url);
		pubUrl = (EditText) findViewById(R.id.editText_pub_url);
		bufferTime = (EditText) findViewById(R.id.editText_buffersize);
		maxBufferTime = (EditText) findViewById(R.id.editText_maxbuffersize);
		enablePlayCB = (CheckBox) findViewById(R.id.checkBox_play_log);
		enableVideoCB = (CheckBox) findViewById(R.id.CheckBox_video);

		playUrl.setText(SharedPreUtil.getString(this, "playUrl", "rtmp://play.nodemedia.cn/NodeMedia/stream"));
		pubUrl.setText(SharedPreUtil.getString(this, "pubUrl",
				"rtmp://pub.nodemedia.cn/NodeMedia/stream_" + Math.round((Math.random() * 1000 + 1000))));
		bufferTime.setText(SharedPreUtil.getString(this, "bufferTime", "300"));
		maxBufferTime.setText(SharedPreUtil.getString(this, "maxBufferTime", "1000"));
		enablePlayCB.setChecked(SharedPreUtil.getBoolean(this, "enablePlayLog"));
		enableVideoCB.setChecked((Boolean) SharedPreUtil.getBoolean(this, "enableVideo"));

		playerBtn.setOnClickListener(this);
		encoderBtn.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button1:
			// 记住上次播放配置的信息，只在demo中使用，非SDK方法
			SharedPreUtil.put(LiveMainActivity.this, "playUrl", playUrl.getText().toString());
			SharedPreUtil.put(LiveMainActivity.this, "bufferTime", bufferTime.getText().toString());
			SharedPreUtil.put(LiveMainActivity.this, "maxBufferTime", maxBufferTime.getText().toString());
			SharedPreUtil.put(LiveMainActivity.this, "enablePlayLog", enablePlayCB.isChecked());
			SharedPreUtil.put(LiveMainActivity.this, "enableVideo", enableVideoCB.isChecked());

			LiveMainActivity.this.startActivity(new Intent(LiveMainActivity.this, LivePlayerDemoActivity.class));
			break;
		case R.id.button2:
			// 记住上次输入的发布地址，只在demo中使用，非SDK方法
			SharedPreUtil.put(LiveMainActivity.this, "pubUrl", pubUrl.getText().toString());

			LiveMainActivity.this.startActivity(new Intent(LiveMainActivity.this, LivePublisherDemoActivity.class));
			break;
		}
	}
}
