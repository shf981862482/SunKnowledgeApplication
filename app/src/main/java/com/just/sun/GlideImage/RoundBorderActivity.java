package com.just.sun.GlideImage;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.common.image.RoundedCornersBorderTransformation;
import com.just.sun.R;
import com.walking_men.sun.sunmultilibrary.utils.DensityUtility;


public class RoundBorderActivity extends Activity {
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round_border);
        imageView = (ImageView) findViewById(R.id.img);
        Glide.with(this)
                .load(R.drawable.beautiful_b)
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .centerCrop()
        .bitmapTransform(new RoundedCornersBorderTransformation(RoundBorderActivity.this,
                        DensityUtility.dip2px(RoundBorderActivity.this, 5), 0,
                        getResources().getColor(R.color.primary),
                        DensityUtility.dip2px(RoundBorderActivity.this, 1)))
                .into(imageView);
    }
}
