package com.just.sun.rs;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.just.sun.R;
import android.support.v8.renderscript.*;
import android.widget.ImageView;

public class MonoRsActivity extends Activity {
    private RenderScript mRS;
    private Allocation mInAllocation;
    private Allocation mOutAllocation;
    private ScriptC_mono mScript;

    private Bitmap mBitmapIn;   //图片资源
    private Bitmap mBitmapOut; //输出图片


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mono_rs);

        mBitmapIn = loadBitmap(R.drawable.ic_launcher);
        mBitmapOut = Bitmap.createBitmap(mBitmapIn.getWidth(), mBitmapIn.getHeight(),
                mBitmapIn.getConfig());//创建了视图空间

        ImageView in = (ImageView) findViewById(R.id.displayin);
        in.setImageBitmap(mBitmapIn);

        ImageView out = (ImageView) findViewById(R.id.displayout);
        out.setImageBitmap(mBitmapOut);
        createScript();
    }

    private void createScript() {
        mRS = RenderScript.create(this);
        mInAllocation = Allocation.createFromBitmap(mRS, mBitmapIn,
                Allocation.MipmapControl.MIPMAP_NONE,
                Allocation.USAGE_SCRIPT);
        mOutAllocation = Allocation.createTyped(mRS, mInAllocation.getType());
        //获得和位图一相同的属性
        mScript = new ScriptC_mono(mRS, getResources(), R.raw.mono);//实例化RenderScript中的函数
        mScript.forEach_root(mInAllocation, mOutAllocation);        //调用RenderScript中的函数
        mOutAllocation.copyTo(mBitmapOut);
    }

    /***********************************************************************
     * 装入图片资源
     * @param resource
     * @return
     */
    private Bitmap loadBitmap(int resource) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        return BitmapFactory.decodeResource(getResources(), resource, options);
    }
}
