package com.common.utils;

import android.app.Activity;
import android.content.Context;
import android.view.WindowManager;

public class DisplayUtil {

	public static int getScreenHeight(Context context) {
		WindowManager wm = ((Activity) context).getWindowManager();
		return wm.getDefaultDisplay().getHeight();
	}

	@SuppressWarnings("deprecation")
	public static int getScreenWidth(Context context) {
		WindowManager wm = ((Activity) context).getWindowManager();
		return wm.getDefaultDisplay().getWidth();
	}

}
