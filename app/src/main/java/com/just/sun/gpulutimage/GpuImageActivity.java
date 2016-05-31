package com.just.sun.gpulutimage;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.just.sun.R;
import com.just.sun.gpulutimage.adapter.ImgFragPageAdapter;
import com.just.sun.gpulutimage.fragment.GpuImageFragment;

import java.util.ArrayList;
import java.util.List;

public class GpuImageActivity extends FragmentActivity implements GpuImageFragment.OnFragmentInteractionListener {
    private ViewPager vp_img;


    private List<Fragment> fragments = new ArrayList<>();

    public int[] luts = new int[]{
            R.drawable.filter_lut_1,
            R.drawable.filter_lut_2,
            R.drawable.filter_lut_3,
            R.drawable.filter_lut_4,
            R.drawable.filter_lut_5,
            R.drawable.filter_lut_6};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gpu_image);
        vp_img = (ViewPager) findViewById(R.id.vp_img);
        for (int i = 0; i < 5; i++) {
            fragments.add(GpuImageFragment.newInstance(i, luts[i]));
        }
        vp_img.setAdapter(new ImgFragPageAdapter(GpuImageActivity.this.getSupportFragmentManager(), fragments));
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
