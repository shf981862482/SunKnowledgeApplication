package com.just.sun.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.just.sun.R;
import com.just.sun.live.BiliPlayerActivity;


public class CardViewFragment extends Fragment implements View.OnClickListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private CardView card_view_player;


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
        card_view_player.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.card_view_player:
                startActivity(new Intent(getContext(), BiliPlayerActivity.class));
                break;
        }
    }
}
