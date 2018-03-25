package com.example.admin.view.adapter.base;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by xyp on 2018/3/18.
 */

public class CommonViewHolder extends RecyclerView.ViewHolder {
    protected View rootView;//根布局
    protected SparseArray<View> childViews;//子view的集合

    private CommonViewHolder(View itemView) {
        super(itemView);
        rootView = itemView;
        childViews = new SparseArray<>();
    }

    public static CommonViewHolder createViewHolder(ViewGroup parent, int layoutId) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        return new CommonViewHolder(view);
    }

    /**
     * 获取根布局
     */
    public View getRootView() {
        return rootView;
    }

    /**
     * 获取布局中的view
     */
    public <T extends View> View getView(int id) {
        View view = childViews.get(id);
        if (view == null) {
            view = rootView.findViewById(id);
            childViews.put(id, view);
        }
        return (T) view;
    }
}
