package com.just.sun.activitys;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v8.renderscript.Allocation;
import android.support.v8.renderscript.Element;
import android.support.v8.renderscript.RenderScript;
import android.support.v8.renderscript.ScriptIntrinsicLUT;
import android.view.View;
import android.widget.ImageView;

import com.just.sun.R;

public class LutScriptActivity extends Activity {
    private Allocation mInAllocation;
    private Allocation mOutAllocation;
    private Bitmap mBitmapIn, mBitmapOut;
    private ScriptIntrinsicLUT mIntrinsic;
    private RenderScript mRS;

    private ImageView imageView;

    private int where = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lut_script);
        imageView = (ImageView) findViewById(R.id.img);
        findViewById(R.id.btn_lut).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBitmapIn = loadBitmap(R.drawable.beautiful_b);
                mBitmapOut = Bitmap.createBitmap(mBitmapIn.getWidth(),
                        mBitmapIn.getHeight(), mBitmapIn.getConfig());
                mRS = RenderScript.create(LutScriptActivity.this);

                mIntrinsic = ScriptIntrinsicLUT.create(mRS,
                        Element.U8_4(mRS));
                if (where == 7) {
                    where = 1;
                }
                createLUT(where++);
                mInAllocation = Allocation.createFromBitmap(mRS, mBitmapIn,
                        Allocation.MipmapControl.MIPMAP_NONE,
                        Allocation.USAGE_SCRIPT);
                mOutAllocation = Allocation.createTyped(mRS, mInAllocation.getType());
                mIntrinsic.forEach(mInAllocation, mOutAllocation);
                mOutAllocation.copyTo(mBitmapOut);
                imageView.setImageBitmap(mBitmapOut);
            }
        });

        findViewById(R.id.recover).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setImageBitmap(mBitmapIn);
            }
        });

    }

    private void createLUT(int type) {
        float value = 0.5f;
        switch (type) {
            case 1:
                value = 0.5f;
                break;
            case 2:
                value = 0.52f;
                break;
            case 3:
                value = 0.54f;
                break;
            case 4:
                value = 0.56f;
                break;
            case 5:
                value = 0.58f;
                break;
            case 6:
                value = 0.6f;
                break;
        }

        for (int ct = 0; ct < 256; ct++) {
            float f = ((float) ct) / 255.f;

            float r = f;
            if (r < value) {
                r = 4.0f * r * r * r;
            } else {
                r = 1.0f - r;
                r = 1.0f - (4.0f * r * r * r);
            }
//            mIntrinsic.setRed(ct, (int) (r * 255.f + 0.5f));
            mIntrinsic.setRed(ct, (int) (r * 255.f + value));

            float g = f;
            if (g < value) {
                g = 2.0f * g * g;
            } else {
                g = 1.0f - g;
                g = 1.0f - (2.0f * g * g);
            }
//            mIntrinsic.setGreen(ct, (int) (g * 255.f + 0.5f));
            mIntrinsic.setGreen(ct, (int) (g * 255.f + value));

//            float b = f * 0.5f + 0.25f;
            float b = f * value + 0.25f;
//            mIntrinsic.setBlue(ct, (int) (b * 255.f + 0.5f));
            mIntrinsic.setBlue(ct, (int) (b * 255.f + value));
        }
    }

    private Bitmap loadBitmap(int resource) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        return BitmapFactory.decodeResource(getResources(), resource, options);
    }
}
