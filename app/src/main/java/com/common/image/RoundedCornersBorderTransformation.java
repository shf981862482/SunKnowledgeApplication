package com.common.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapResource;

/**
 * Created by walkingMen on 2016/6/20.
 */
public class RoundedCornersBorderTransformation implements Transformation<Bitmap> {


    private BitmapPool mBitmapPool;
    private int mRadius;
    private int mDiameter;
    private int mMargin;
    private int borderWidth;
    private int borderColor;

    public RoundedCornersBorderTransformation(Context context, int radius, int margin,int borderColor,int borderWidth) {
        this(Glide.get(context).getBitmapPool(), radius, margin,borderColor,borderWidth);
    }

    public RoundedCornersBorderTransformation(BitmapPool pool, int radius, int margin,int borderColor,int borderWidth) {
        mBitmapPool = pool;
        mRadius = radius;
        mDiameter = mRadius * 2;
        mMargin = margin;
        this.borderColor = borderColor;
        this.borderWidth = borderWidth;
    }

    @Override
    public Resource<Bitmap> transform(Resource<Bitmap> resource, int outWidth, int outHeight) {
        Bitmap source = resource.get();

        int width = source.getWidth();
        int height = source.getHeight();

        Bitmap bitmap = mBitmapPool.get(width, height, Bitmap.Config.ARGB_8888);
        if (bitmap == null) {
            bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setShader(new BitmapShader(source, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
        drawRoundRect(canvas, paint, width, height);
        setBitmapBorder(canvas, width, height);
        return BitmapResource.obtain(bitmap, mBitmapPool);
    }

    /**
     * 给bitmap设置边框
     *
     * @param canvas
     */
    private void setBitmapBorder(Canvas canvas, float width, float height) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);//抗锯齿
        //设置边框颜色
        paint.setColor(borderColor);
        paint.setStyle(Paint.Style.STROKE);
        //设置边框宽度
        paint.setStrokeWidth(borderWidth);
        canvas.drawRoundRect(new RectF(mMargin, mMargin, width, height), mRadius, mRadius, paint);
    }

    private void drawRoundRect(Canvas canvas, Paint paint, float width, float height) {
        float right = width - mMargin;
        float bottom = height - mMargin;

        canvas.drawRoundRect(new RectF(mMargin, mMargin, right, bottom), mRadius, mRadius, paint);
    }


    @Override
    public String getId() {
        return "RoundedTransformation(radius=" + mRadius + ", margin=" + mMargin + ", diameter="
                + mDiameter;
    }
}
