package com.just.sun.GlideImage;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.common.image.RoundedCornersBorderTransformation;
import com.just.sun.R;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class RoundBorderActivity extends Activity {
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round_border);
        imageView = (ImageView) findViewById(R.id.img);
        Glide.with(this)
                .load(R.drawable.beautiful)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .bitmapTransform(new RoundedCornersBorderTransformation(RoundBorderActivity.this, 30, 0, R.color.colorPrimary, 10))
                .into(imageView);
    }
}
