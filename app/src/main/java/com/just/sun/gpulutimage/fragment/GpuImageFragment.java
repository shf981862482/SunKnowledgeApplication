package com.just.sun.gpulutimage.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.just.sun.R;
import com.just.sun.utils.GPUImageFilterTools;

import java.util.LinkedList;
import java.util.List;

import jp.co.cyberagent.android.gpuimage.GPUImage;
import jp.co.cyberagent.android.gpuimage.GPUImageColorInvertFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageContrastFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageGammaFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageHueFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageLookupFilter;
import jp.co.cyberagent.android.gpuimage.GPUImagePixelationFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageView;

public class GpuImageFragment extends Fragment {
    private static final String ARG_PARAM_POSITION = "ARG_PARAM_POSITION";
    private static final String ARG_PARAM_RESOURCE = "ARG_PARAM_RESOURCE";

    private int positionNow;
    private int resourceId;

    private OnFragmentInteractionListener mListener;

    public GpuImageFragment() {
    }

    public static GpuImageFragment newInstance(int position, int res) {
        GpuImageFragment fragment = new GpuImageFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM_POSITION, position);
        args.putInt(ARG_PARAM_RESOURCE, res);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            positionNow = getArguments().getInt(ARG_PARAM_POSITION);
            resourceId = getArguments().getInt(ARG_PARAM_RESOURCE);
        }
    }

    private GPUImageView gpuimage;
    private GPUImageFilter mFilter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gpu_image, container, false);
        gpuimage = (GPUImageView) view.findViewById(R.id.gpuimage);
        mFilter = null;//置空、不然不执行switchFilterTo
        Bitmap bitmap = BitmapFactory.decodeResource(getActivity().getResources(), R.drawable.beautiful);
        gpuimage.setImage(bitmap);
        GPUImageLookupFilter amatorka = new GPUImageLookupFilter();
        amatorka.setBitmap(BitmapFactory.decodeResource(getContext().getResources(),resourceId));
        switchFilterTo(amatorka);
        gpuimage.requestRender();
        return view;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    private void switchFilterTo(final GPUImageFilter filter) {
        if (mFilter == null
                || (filter != null && !mFilter.getClass().equals(filter.getClass()))) {
            mFilter = filter;
            gpuimage.setFilter(mFilter);
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
