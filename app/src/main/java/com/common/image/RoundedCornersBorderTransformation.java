package com.common.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapResource;

/**
 * Created by walkingMen on 2016/6/20.
 * centerCrop will lose efficacy
 */
public class RoundedCornersBorderTransformation implements Transformation<Bitmap> {


    private BitmapPool mBitmapPool;
    private int mRadius;
    private int mDiameter;
    private int mMargin;
    private int borderWidth;
    private int borderColor;

    /**
     * @param context
     * @param radius
     * @param margin
     * @param borderColor 边框颜色 支持 context.getResources().getColor(R.color.xxx)
     * @param borderWidth 边框宽度
     */
    public RoundedCornersBorderTransformation(Context context, int radius, int margin, int borderColor, int borderWidth) {
        this(Glide.get(context).getBitmapPool(), radius, margin, borderColor, borderWidth);
    }

    public RoundedCornersBorderTransformation(BitmapPool pool, int radius, int margin, int borderColor, int borderWidth) {
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

        return BitmapResource.obtain(bitmap, mBitmapPool);
    }


    private void drawRoundRect(Canvas canvas, Paint paint, float width, float height) {
        float right = width - mMargin;
        float bottom = height - mMargin;
        setBitmapBorder(canvas, right, bottom);
        float rightIn = width - mMargin - borderWidth;
        float bottomIn = height - mMargin - borderWidth;
        //画圆角图片、去除边框的宽度
        canvas.drawRoundRect(new RectF(mMargin + borderWidth, mMargin + borderWidth, rightIn, bottomIn),
                mRadius, mRadius, paint);
    }

    /**
     * 给bitmap设置边框
     *
     * @param canvas
     */
    private void setBitmapBorder(Canvas canvas, float right, float bottom) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);//抗锯齿
        //设置边框颜色
        paint.setColor(borderColor);
//        paint.setStyle(Paint.Style.STROKE);
        paint.setStyle(Paint.Style.FILL);
        //设置边框宽度
//        paint.setStrokeWidth(borderWidth);
        canvas.drawRoundRect(new RectF(mMargin, mMargin, right, bottom), mRadius, mRadius, paint);
    }


    @Override
    public String getId() {
        return "RoundedTransformation(radius=" + mRadius + ", margin=" + mMargin + ", diameter="
                + mDiameter;
    }
}
