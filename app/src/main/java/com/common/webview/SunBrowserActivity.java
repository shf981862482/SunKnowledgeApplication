package com.common.webview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.os.Process;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import com.common.base.BaseFragmentActivity;
import com.common.common.SerializableMap;
import com.common.common.SunConstant;
import com.common.utils.Logger;
import com.just.sun.R;
import com.just.sun.databinding.ActivitySunBrowserBinding;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by walkingMen on 16/3/11.
 * H5页面
 */
public class SunBrowserActivity extends BaseFragmentActivity {

    public static final String EXTRA_TAG = "SHF";
    public static final String EXTRA_SINA_WEIBO = "EXTRA_SINA_WEIBO";
    public static final String EXTRA_INITURL = "INITURL";
    public static final String EXTRA_ARGS = "ARGS";

    private ActivitySunBrowserBinding mBinding;

    private WebView webView;
    private LinearLayout loadingLayout;
    private static final String SINA_WEIBO_PREFIX = "http://m.weibo.cn/u/";
    //TODO
    private Map<String, String> headers;
    private Bundle bundle;
    private String tag;
    private boolean have302 = false;
    private boolean firstIn = true;

    private ValueCallback<Uri> mUploadMessage;
    private final static int FILECHOOSER_RESULTCODE = 1;
    private String initUrl = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindingView();
        init();
    }


    protected void bindingView() {
        this.getWindow().requestFeature(Window.FEATURE_PROGRESS);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_sun_browser);
        webView = mBinding.sinaWeiboWebviewId;
        loadingLayout = (LinearLayout) findViewById(R.id.loading_data_tips);
    }

    protected void init() {
        Logger.d(EXTRA_TAG, "init");
        bundle = this.getIntent().getExtras();
        tag = bundle.getString(EXTRA_TAG);
        boolean hasWeibo = bundle.getBoolean(EXTRA_SINA_WEIBO);
        if (hasWeibo) {// 新浪微博url特殊处理，其他直接显示
            String sinaUserId = bundle.getString(EXTRA_INITURL);
            initUrl = createSinaBlogUrl(sinaUserId);
        } else {
            initUrl = bundle.getString(EXTRA_INITURL);
        }

        SerializableMap map = (SerializableMap) bundle.getSerializable(EXTRA_ARGS);
        if (map != null) {
            headers = map.getMap();
        } else {
            headers = new HashMap<String, String>();
        }


//        headers.put("xingyunFromApp", "Android");//判断是android客户端还是
//        headers.put("Authorization", "Basic " + LoginDataCenterManager.getInstance().getToken());//h5页面请求头获取用户信息
//        headers.put("Authorization", "Basic ");//h5页面请求头获取用户信息

        if (!TextUtils.isEmpty(initUrl) && !initUrl.toLowerCase().startsWith(SunConstant.HTTP_PREFIX)) {
            initUrl = SunConstant.HTTP_PREFIX + initUrl;
        }

        Logger.d(EXTRA_TAG, "init url : " + initUrl);
        loadUrl(initUrl);
    }

    private String createSinaBlogUrl(String sinaUserId) {
        return SINA_WEIBO_PREFIX + sinaUserId;
    }

    @SuppressWarnings("deprecation")
    @SuppressLint("SetJavaScriptEnabled")
    private void loadUrl(String url) {
        WebSettings settings = webView.getSettings();
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);

        // settings.setBuiltInZoomControls(true);
        // settings.setSupportZoom(true);
        // settings.setDisplayZoomControls(false);//不显示缩放控件

        // settings.setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
        // settings.setTextSize(TextSize.SMALLEST);

        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setAllowContentAccess(true);
        settings.setPluginState(WebSettings.PluginState.ON);

        settings.setDomStorageEnabled(true);
        settings.setAppCacheEnabled(true);
        String appCacheDir = this.getApplicationContext().getDir("cache", Context.MODE_PRIVATE).getPath();
        settings.setAppCachePath(appCacheDir);
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        settings.setAppCacheMaxSize(1024 * 1024 * 10);// 设置缓冲大小，10M
        settings.setAllowFileAccess(true);
        settings.setRenderPriority(WebSettings.RenderPriority.HIGH);

//		settings.setBlockNetworkImage(true);// 把图片加载放在最后来加载渲染

        if (headers != null && headers.size() > 0) {
            webView.loadUrl(url, headers);
        } else {
            webView.loadUrl(url);
        }

        webView.setWebChromeClient(new MyWebClient());

        webView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (!TextUtils.isEmpty(url) && !TextUtils.isEmpty(initUrl) && !url.equals(initUrl) && firstIn && webProgress > 100) {
                    firstIn = false;
                    have302 = true;
                    Logger.d(EXTRA_TAG, "302啦");
                }
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                loadingLayout.setVisibility(View.GONE);
                String title = view.getTitle();
            }

        });
    }


    private int webProgress = 0;

    public class MyWebClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            webProgress = newProgress * 100;
            // 重写方法 设置网页加载进度条 立马显得高大上了有没有
            Logger.d(EXTRA_TAG, "progress : " + webProgress);
            if (webProgress > 5000) {
                Logger.d(EXTRA_TAG, "网页加载一半 : " + webProgress);
                loadingLayout.setVisibility(View.GONE);
            }
            SunBrowserActivity.this.setProgress(webProgress);
            if (webProgress >= 10000) {
                firstIn = false;
            }
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            Logger.d(EXTRA_TAG, "title:" + title);
            //这里设置web的title;


        }

        // For Android 3.0-
        public void openFileChooser(ValueCallback<Uri> uploadMsg) {
            mUploadMessage = uploadMsg;
            Intent i = new Intent(Intent.ACTION_GET_CONTENT);
            i.addCategory(Intent.CATEGORY_OPENABLE);
            i.setType("image/*");
            startActivityForResult(Intent.createChooser(i, "File Chooser"), FILECHOOSER_RESULTCODE);
        }

        // For Android 3.0+
        public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType) {
            mUploadMessage = uploadMsg;
            Intent i = new Intent(Intent.ACTION_GET_CONTENT);
            i.addCategory(Intent.CATEGORY_OPENABLE);
            i.setType("*/*");
            startActivityForResult(Intent.createChooser(i, "File Browser"), FILECHOOSER_RESULTCODE);
        }

        // For Android 4.1
        public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
            mUploadMessage = uploadMsg;
            Intent i = new Intent(Intent.ACTION_GET_CONTENT);
            i.addCategory(Intent.CATEGORY_OPENABLE);
            i.setType("image/*");
            startActivityForResult(Intent.createChooser(i, "File Chooser"), FILECHOOSER_RESULTCODE);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == FILECHOOSER_RESULTCODE) {
            if (null == mUploadMessage) {
                return;
            }

            Uri result = intent == null || resultCode != RESULT_OK ? null : intent.getData();
            mUploadMessage.onReceiveValue(result);
            mUploadMessage = null;
        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Logger.d(EXTRA_TAG, "onKeyDown KEYCODE_BACK");
            backClick();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void backClick() {
        if (webView != null && webView.canGoBack() && !have302) {
            webView.goBack();
            String title = webView.getTitle();
            Logger.d(EXTRA_TAG, "go back title : " + title);

            // 防止重定向到另外一个页面，按物理返回键不退出activity的问题
/*            if (webProgress >= 10000) {
                finish();
            }*/
        } else {
            finish();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (webView != null) {// 如果播放视频，界面不在最高级时暂停播放
            webView.onPause();
        }
    }

    @Override
    protected void onDestroy() {
        if (webView != null) {
            webView.removeAllViews();
            webView.destroy();
        }
        super.onDestroy();
        Process.killProcess(Process.myPid());
    }

    protected void onResume() {
        super.onResume();
        if (webView != null) {
            webView.onResume();
        }
    }

}