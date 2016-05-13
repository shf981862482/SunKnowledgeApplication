package com.just.sun.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.common.utils.Logger;
import com.just.sun.R;

/**
 * Created by walkingMen on 2016/4/27.
 */
public class LineLoadingProgressBar {
    protected static final String TAG = "XyProgressBar";
    private PopupWindow pw;
    private View progressView;
    private TextView tvTips;

    public static LineLoadingProgressBar showDialog(Context context) {
        LineLoadingProgressBar progressBar = new LineLoadingProgressBar(context);
        progressBar.show();
        return progressBar;
    }

    public LineLoadingProgressBar(Context context) {
        progressView = LayoutInflater.from(context).inflate(R.layout.layout_line_loading, null);
        tvTips = (TextView) progressView.findViewById(R.id.tv_progress);
        pw = new PopupWindow(progressView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT,
                true);
        pw.setTouchable(false);
        pw.setOutsideTouchable(false);
        pw.setFocusable(false);
        pw.setBackgroundDrawable(new BitmapDrawable(context.getResources(), (Bitmap) null));
    }

    public LineLoadingProgressBar(Context context, boolean touchable) {
        View progressView = LayoutInflater.from(context).inflate(R.layout.layout_line_loading,
                null);
        pw = new PopupWindow(progressView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT,
                true);
        pw.setFocusable(false);
        pw.setTouchable(touchable);
        pw.setOutsideTouchable(touchable);
        pw.setBackgroundDrawable(new BitmapDrawable(context.getResources(), (Bitmap) null));
    }

    public void setOutsideTouchable(boolean touchable) {
        pw.setTouchable(touchable);
        pw.setOutsideTouchable(touchable);
    }

    public void setFocusable(boolean focusable) {
        pw.setFocusable(false);
    }

    public void setProgressText(String text) {
        tvTips.setText(text);
    }

    public void setProgressText(int resId) {
        tvTips.setText(resId);
    }

    public boolean isShowing() {
        if (!pw.isShowing()) {
            return false;
        }
        return true;
    }

    public void show() {
        try {
            pw.showAtLocation(progressView, Gravity.CENTER, 0, 0);
        } catch (Exception e) {
            Logger.d(TAG, "show", e);
        }
    }

    public void show(int x, int y) {
        try {
            pw.showAtLocation(progressView, Gravity.LEFT, x, y);
        } catch (Exception e) {
            Logger.d(TAG, "show", e);
        }
    }

    public void hide() {
        hidePowWindow();
    }

    public void dismiss() {
        hidePowWindow();
    }

    private void hidePowWindow() {
        if (pw != null && pw.isShowing()) {
            try {
                pw.dismiss();
            } catch (Exception e) {
                Logger.d(TAG, "hidePowWindow", e);
            }
        }
    }
}
