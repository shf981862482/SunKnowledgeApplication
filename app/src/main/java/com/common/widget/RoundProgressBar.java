package com.common.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.common.common.Global;
import com.just.sun.R;
import com.walking_men.sun.sunmultilibrary.utils.DensityUtility;


/**
 * 仿iphone带进度的进度条，线程安全的View，可直接在线程中更新进度
 *
 * @author sun
 */
public class RoundProgressBar extends View {
    private static final String TAG = "RoundProgressBar";

    // 画实心圆的画笔
    private Paint mCirclePaint;
    // 画圆环的画笔
    private Paint mRingPaint;
    // 画字体的画笔
    private Paint mTextPaint;
    // 圆形颜色
    private int mCircleColor;
    // 圆环颜色
    private int mRingColor;
    // 半径
    private float mRadius;
    // 圆环半径
    private float mRingRadius;
    // 圆环宽度
    private float mStrokeWidth;
    // 圆心x坐标
    private int mXCenter;
    // 圆心y坐标
    private int mYCenter;
    // 字的长度
    private float mTxtWidth;
    // 字的高度
    private float mTxtHeight;
    // 总进度
    private int mTotalProgress = MaxTime;
    // 当前进度
    private int mProgress;
//==============================

    float sweepAngle;
    float left;
    float top;
    float right;
    float bottom;

    //=========================
    private int values = 0;
    private int count = 0;
    private static int MaxTime = 1000;
    private static final int COMPLETED = 101;
    private LianJiListener lianJiListener;

    private Handler mWorkHandler = new Handler(Global.getWorkThreadLooper()) {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case COMPLETED:
                    lianJiListener.complete();
                    break;
            }
        }
    };

    private Handler mMainHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case COMPLETED:
                    lianJiListener.complete();
                    break;
            }
        }
    };
    private RectF oval;

    //=========================

    public interface LianJiListener {
        void count(int count);

        void complete();
    }

    public LianJiListener getLianJiListener() {
        return lianJiListener;
    }

    public void setLianJiListener(LianJiListener lianJiListener) {
        this.lianJiListener = lianJiListener;
    }
    //=========================

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            setProgress(values);
            if (values < MaxTime) {
                mWorkHandler.postDelayed(this, 16);
            } else {
                mWorkHandler.removeCallbacks(this); // 停止计时器
                mMainHandler.sendEmptyMessage(COMPLETED);

            }
            values += 15;
        }
    };

    //=========================
    public void reStart() {
        mWorkHandler.removeCallbacks(runnable); // 停止计时器
        values = 0;
        count++;
        lianJiListener.count(count);
        mWorkHandler.postDelayed(runnable, 0);// 每两秒执行一次runnable.
    }

    //=========================
    public void clear() {
        mWorkHandler.removeCallbacks(runnable); // 停止计时器
        values = 0;
        count = 0;
        postInvalidate();
    }


    public RoundProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        // 获取自定义的属性
        initAttrs(context, attrs);
        initVariable();
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray typeArray = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.TasksCompletedView, 0, 0);
        mRadius = typeArray.getDimension(R.styleable.TasksCompletedView_radius, 80);
        mStrokeWidth = typeArray.getDimension(R.styleable.TasksCompletedView_lianji_strokeWidth, 5);
        mCircleColor = typeArray.getColor(R.styleable.TasksCompletedView_circleColor, 0xFFFFFFFF);
        mRingColor = typeArray.getColor(R.styleable.TasksCompletedView_ringColor, 0xFFFFFFFF);

        mRingRadius = mRadius + mStrokeWidth / 2;
    }

    private void initVariable() {
        mCirclePaint = new Paint();
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setColor(mCircleColor);
        mCirclePaint.setStyle(Paint.Style.FILL);

        mRingPaint = new Paint();
        mRingPaint.setAntiAlias(true);
        mRingPaint.setColor(mRingColor);
        mRingPaint.setStyle(Paint.Style.STROKE);
        mRingPaint.setStrokeWidth(mStrokeWidth);

        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setStyle(Paint.Style.FILL);
        mTextPaint.setARGB(255, 255, 255, 255);
        mTextPaint.setTextSize(mRadius / 2);

        Paint.FontMetrics fm = mTextPaint.getFontMetrics();
        mTxtHeight = (int) Math.ceil(fm.descent - fm.ascent);


        oval = new RectF();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.d(TAG, "onDraw: " + canvas.isHardwareAccelerated());

        if (mXCenter == 0) {
            mXCenter = getWidth() / 2;
            mYCenter = getHeight() / 2;
        }
        canvas.drawCircle(mXCenter, mYCenter, mRadius - 4, mCirclePaint);
        if (count > 0) {

            oval.left = left;
            oval.top = top;
            oval.right = right;
            oval.bottom = bottom;
            canvas.drawArc(oval, -90, sweepAngle, false, mRingPaint); //

            String txt = String.valueOf(count);
            mTxtWidth = mTextPaint.measureText(txt, 0, txt.length());
            String txtLianji = "连 击";
            float txtLianjiWidth = mTextPaint.measureText(txtLianji, 0, txtLianji.length());
            int top = DensityUtility.dip2px(Global.mContext,10);
            int button = DensityUtility.dip2px(Global.mContext,6);
            canvas.drawText(txt, mXCenter - mTxtWidth / 2, mYCenter + mTxtHeight / 4 + top, mTextPaint);
            canvas.drawText(txtLianji, mXCenter - txtLianjiWidth / 2, mYCenter - button, mTextPaint);
        }
    }

    public void setProgress(int progress) {
        mProgress = progress;
        left = mXCenter - mRingRadius;
        top = mYCenter - mRingRadius;
        right = mRingRadius * 2 + (mXCenter - mRingRadius);
        bottom = mRingRadius * 2 + (mYCenter - mRingRadius);
        sweepAngle = ((float) (mTotalProgress - mProgress) / mTotalProgress) * -360;
        postInvalidate();
    }

}
