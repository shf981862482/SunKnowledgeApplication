package com.just.sun.listener;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.just.sun.GlideImage.RoundBorderActivity;
import com.just.sun.activitys.DanmuActivity;
import com.just.sun.activitys.FramlayoutActivity;
import com.just.sun.activitys.LoadingActivity;
import com.just.sun.activitys.LoadingBasicActivity;
import com.just.sun.activitys.LoadingDialogActivity;
import com.just.sun.activitys.MemoryAnalyzeActivity;
import com.just.sun.activitys.PropertyAnimationActivity;
import com.just.sun.activitys.SunDanmuActivity;
import com.just.sun.activitys.ZanActivity;
import com.just.sun.dagger.activity.FruitActivity;
import com.just.sun.databinding.FragmentOtherBinding;
import com.just.sun.jni.JniHelloActivity;
import com.just.sun.live.LiveMainActivity;
import com.just.sun.model.OtherModel;
import com.just.sun.rs.MonoRsActivity;

/**
 * Created by walkingMen on 2016/4/27.
 */
public class OtherListener {
    private OtherModel model;
    private FragmentOtherBinding binding;

    public OtherListener(OtherModel model, FragmentOtherBinding binding) {
        this.model = model;
        this.binding = binding;
    }

    public AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            switch (position) {
                case 0:
                    view.getContext().startActivity(new Intent(view.getContext(), DanmuActivity.class));
                    break;
                case 1:
                    view.getContext().startActivity(new Intent(view.getContext(), LoadingActivity.class));
                    break;
                case 2:
                    view.getContext().startActivity(new Intent(view.getContext(), LoadingBasicActivity.class));
                    break;
                case 3:
                    view.getContext().startActivity(new Intent(view.getContext(), LoadingDialogActivity.class));
                    break;
                case 4:
                    view.getContext().startActivity(new Intent(view.getContext(), FramlayoutActivity.class));
                    break;
                case 5:
                    view.getContext().startActivity(new Intent(view.getContext(), ZanActivity.class));
                    break;
                case 6:
                    view.getContext().startActivity(new Intent(view.getContext(), PropertyAnimationActivity.class));
                    break;
                case 7:
                    view.getContext().startActivity(new Intent(view.getContext(), SunDanmuActivity.class));
                    break;
                case 8:
                    view.getContext().startActivity(new Intent(view.getContext(), LiveMainActivity.class));
                    break;
                case 9:
                    view.getContext().startActivity(new Intent(view.getContext(), MonoRsActivity.class));
                    break;
                case 10:
                    view.getContext().startActivity(new Intent(view.getContext(), JniHelloActivity.class));
                    break;
                case 11:
                    view.getContext().startActivity(new Intent(view.getContext(), FruitActivity.class));
                    break;
                case 12:
                    view.getContext().startActivity(new Intent(view.getContext(), RoundBorderActivity.class));
                    break;
                case 13:
                    view.getContext().startActivity(new Intent(view.getContext(), MemoryAnalyzeActivity.class));
                    break;
            }
        }
    };
}
