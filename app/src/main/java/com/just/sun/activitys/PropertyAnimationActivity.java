package com.just.sun.activitys;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;

import com.just.sun.R;
import com.just.sun.utils.DensityUtility;

public class PropertyAnimationActivity extends Activity {
    private ImageView top, left;
    private Button show, hide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_animation);
        top = (ImageView) findViewById(R.id.top);
        left = (ImageView) findViewById(R.id.left);
        show = (Button) findViewById(R.id.show);
        hide = (Button) findViewById(R.id.hide);

        int leftL = DensityUtility.dip2px(PropertyAnimationActivity.this, 20 + 30);
        int topL = DensityUtility.dip2px(PropertyAnimationActivity.this, 20 + 30);
        PropertyValuesHolder _translationX = PropertyValuesHolder.ofFloat("translationX", 0f, -leftL);
        PropertyValuesHolder _translationY = PropertyValuesHolder.ofFloat("translationY", 0f, -topL);
        PropertyValuesHolder translationX = PropertyValuesHolder.ofFloat("translationX", -leftL, 0f);
        PropertyValuesHolder translationY = PropertyValuesHolder.ofFloat("translationY", -topL, 0f);
        final ObjectAnimator objectAnimatorTopShow = ObjectAnimator.ofPropertyValuesHolder(top, translationY);
        final ObjectAnimator objectAnimatorLeftShow = ObjectAnimator.ofPropertyValuesHolder(left, translationX);
        final ObjectAnimator objectAnimatorTopHide = ObjectAnimator.ofPropertyValuesHolder(top, _translationY);
        final ObjectAnimator objectAnimatorLeftHide = ObjectAnimator.ofPropertyValuesHolder(left, _translationX);
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                objectAnimatorTopShow.start();
                objectAnimatorLeftShow.start();
            }
        });

        hide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                objectAnimatorTopHide.start();
                objectAnimatorLeftHide.start();
            }
        });
    }
}
