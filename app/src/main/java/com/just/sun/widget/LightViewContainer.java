package com.just.sun.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.just.sun.R;

/**
 * Created by walkingMen on 2016/5/5.
 */
public class LightViewContainer extends View {
    private Paint paint;
    public LightViewContainer(Context context) {
        super(context);
        init();
    }


    public LightViewContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LightViewContainer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public LightViewContainer(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        paint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
//        canvas.drawBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.zan_light_done));
    }
}
