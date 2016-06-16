package com.just.sun.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.just.sun.databinding.FragmentOtherBinding;
import com.just.sun.listener.OtherListener;
import com.just.sun.model.OtherModel;
import com.just.sun.model.SimpleModel;


public class OtherFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private OtherModel model;
    private FragmentOtherBinding binding;
    private OtherListener listener;

    private Context context;

    public OtherFragment() {
    }


    public static OtherFragment newInstance(String param1, String param2) {
        OtherFragment fragment = new OtherFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        context = getContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentOtherBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        binding.setModel(model);
        binding.setListener(listener);
    }

    private void init() {
        model = new OtherModel();
        model.items.add(new SimpleModel("弹幕"));
        model.items.add(new SimpleModel("SLoadingIndicatorView"));
        model.items.add(new SimpleModel("加载库"));
        model.items.add(new SimpleModel("LoadingDialog"));
        model.items.add(new SimpleModel("FramLayout"));
        model.items.add(new SimpleModel("点赞"));
        model.items.add(new SimpleModel("属性动画"));
        model.items.add(new SimpleModel("sun弹幕"));
        model.items.add(new SimpleModel("node_media_live"));
        model.items.add(new SimpleModel("renderscrip_mono"));
        model.items.add(new SimpleModel("jni_hello"));
        model.items.add(new SimpleModel("dagger"));

        listener = new OtherListener(model, binding);
    }

}
