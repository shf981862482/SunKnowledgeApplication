package com.just.sun.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.just.sun.R;

import java.util.ArrayList;
import java.util.List;

public class CardViewFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private List<String> titles;

    private CardView cardView;


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
        Log.i("SHF","CardViewFragment--->onCreateView");
        View view = inflater.inflate(R.layout.fragment_card_view, container, false);
        init(view);


        return view;
    }

    private void init(View view) {
        titles = new ArrayList<>();

        titles.add("热门");
        titles.add("特惠");
        titles.add("门票");
        titles.add("线路");
        titles.add("国际");


    }

}
