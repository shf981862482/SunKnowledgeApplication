package com.just.sun.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.common.webview.WebViewUIManager;
import com.just.sun.R;
import com.just.sun.activitys.LutScriptActivity;
import com.just.sun.gpulutimage.GpuImageActivity;
import com.just.sun.live.BiliPlayerActivity;
import com.just.sun.live.ComboClickActivity;


public class CardViewFragment extends Fragment implements View.OnClickListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private CardView card_view_player;
    private CardView card_view_gpu_img;
    private CardView card_view_combo_click;
    private CardView card_view_baidu;


    public CardViewFragment() {
    }


    public static CardViewFragment newInstance(String param1, String param2) {
        CardViewFragment fragment = new CardViewFragment();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_card_view, container, false);
        init(view);

        return view;
    }

    private void init(View view) {
        card_view_player = (CardView) view.findViewById(R.id.card_view_player);
        card_view_gpu_img = (CardView) view.findViewById(R.id.card_view_gpu_img);
        card_view_combo_click = (CardView) view.findViewById(R.id.card_view_combo_click);
        card_view_baidu = (CardView) view.findViewById(R.id.card_view_baidu);
        card_view_baidu.setOnClickListener(this);
        card_view_combo_click.setOnClickListener(this);
        card_view_player.setOnClickListener(this);
        card_view_gpu_img.setOnClickListener(this);
        view.findViewById(R.id.card_view_lut).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.card_view_player:
                startActivity(new Intent(getContext(), BiliPlayerActivity.class));
                break;
            case R.id.card_view_lut:
                startActivity(new Intent(getContext(), LutScriptActivity.class));
                break;
            case R.id.card_view_gpu_img:
                startActivity(new Intent(getContext(), GpuImageActivity.class));
                break;
            case R.id.card_view_combo_click:
                startActivity(new Intent(getContext(), ComboClickActivity.class));
                break;
            case R.id.card_view_baidu:
                WebViewUIManager.openWebViewPage(getContext(),"www.baidu.com");
                break;
        }
    }
}
