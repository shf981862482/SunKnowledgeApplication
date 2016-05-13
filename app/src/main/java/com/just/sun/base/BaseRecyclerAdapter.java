package com.just.sun.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;



/**
 * Created by pc-004 on 2015/12/4.
 */
public class BaseRecyclerAdapter extends RecyclerView.Adapter implements View.OnClickListener  {

    protected OnRecyItemClickListener onRecyItemClickListener;
    protected RecyclerView recyclerView;

    public OnRecyItemClickListener getOnRecyItemClickListener() {
        return onRecyItemClickListener;
    }

    public void setOnRecyItemClickListener(OnRecyItemClickListener onRecyItemClickListener) {
        this.onRecyItemClickListener = onRecyItemClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public void onClick(View v) {
        if (onRecyItemClickListener != null) {
            int position = recyclerView.getChildAdapterPosition(v);
            onRecyItemClickListener.onRecyItemClickListener(v, position);
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    public interface OnRecyItemClickListener {
        void onRecyItemClickListener(View view, int position);
    }
}
