package com.just.sun.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.just.sun.R;

import java.util.ArrayList;
import java.util.List;

import io.vov.vitamio.Vitamio;
import io.vov.vitamio.provider.MediaStore;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

public class VitamioFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private List<String> titles;

    private String path = "http://dlqncdn.miaopai.com/stream/MVaux41A4lkuWloBbGUGaQ__.mp4";
    private VideoView video;
    private Context context = getActivity();


    public VitamioFragment() {
    }


    public static VitamioFragment newInstance(String param1, String param2) {
        VitamioFragment fragment = new VitamioFragment();
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
        Vitamio.isInitialized(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i("SHF", "VitamioFragment--->onCreateView");
        View view = inflater.inflate(R.layout.fragment_vitamio, container, false);

        init(view);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        video.setVideoPath(path);
        video.setMediaController(new MediaController(context));
        video.requestFocus();
    }

    private void init(View view) {
        titles = new ArrayList<>();

        titles.add("热门");
        titles.add("特惠");
        titles.add("门票");
        titles.add("线路");
        titles.add("国际");
        video = (VideoView) view.findViewById(R.id.vitamio_video);



    }

}
