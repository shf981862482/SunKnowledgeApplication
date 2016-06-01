package com.common.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.TintTypedArray;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * Created by walkingMen on 2016/5/31.
 */
public class SunToolBar extends RelativeLayout {

    private int mTitleMarginStart;
    private int mTitleMarginEnd;
    private int mTitleMarginTop;
    private int mTitleMarginBottom;

    public SunToolBar(Context context) {
        super(context);
        init();
    }


    public SunToolBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SunToolBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        final TintTypedArray a = TintTypedArray.obtainStyledAttributes(getContext(), attrs,
                android.support.v7.appcompat.R.styleable.Toolbar, defStyleAttr, 0);
        init(a);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public SunToolBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        final TintTypedArray a = TintTypedArray.obtainStyledAttributes(getContext(), attrs,
                android.support.v7.appcompat.R.styleable.Toolbar, defStyleAttr, 0);
        init(a);
    }

    private void init() {
    }

    private void init(TintTypedArray a) {

        mTitleMarginStart = mTitleMarginEnd = mTitleMarginTop = mTitleMarginBottom =
                a.getDimensionPixelOffset(android.support.v7.appcompat.R.styleable.Toolbar_titleMargins, 0);
        final int marginStart = a.getDimensionPixelOffset(android.support.v7.appcompat.R.styleable.Toolbar_titleMarginStart, -1);
        if (marginStart >= 0) {
            mTitleMarginStart = marginStart;
        }

        final int marginEnd = a.getDimensionPixelOffset(android.support.v7.appcompat.R.styleable.Toolbar_titleMarginEnd, -1);
        if (marginEnd >= 0) {
            mTitleMarginEnd = marginEnd;
        }

        final int marginTop = a.getDimensionPixelOffset(android.support.v7.appcompat.R.styleable.Toolbar_titleMarginTop, -1);
        if (marginTop >= 0) {
            mTitleMarginTop = marginTop;
        }

        final int marginBottom = a.getDimensionPixelOffset(android.support.v7.appcompat.R.styleable.Toolbar_titleMarginBottom,
                -1);
        if (marginBottom >= 0) {
            mTitleMarginBottom = marginBottom;
        }
        RelativeLayout.LayoutParams layoutParams = (LayoutParams) getLayoutParams();
        layoutParams.setMargins(mTitleMarginStart, mTitleMarginTop, mTitleMarginEnd, mTitleMarginBottom);
        setLayoutParams(layoutParams);
    }
}
