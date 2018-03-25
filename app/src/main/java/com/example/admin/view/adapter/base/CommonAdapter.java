package com.example.admin.view.adapter.base;

import android.view.ViewGroup;

import java.util.List;

/**
 * Created by xyp on 2018/3/18.
 * 单布局通用适配器
 */

public abstract class CommonAdapter<T> extends MultiItemTypeAdapter<T> {

    private int layoutId;

    public CommonAdapter(List<T> data, int layoutId) {
        super(data);
        this.layoutId = layoutId;
    }

    @Override
    public int getItemType(int position) {
        return 0;
    }

    @Override
    public CommonViewHolder createHolder(ViewGroup parent, int viewType) {
        return CommonViewHolder.createViewHolder(parent,layoutId);
    }

    @Override
    public int getTypeCount() {
        return 0;
    }

    @Override
    public int getRealPotion(int position) {
        return position;
    }

    @Override
    public boolean isFullSpan(int position) {
        return false;
    }

    //对外的数据绑定
    public abstract void bindHolder(CommonViewHolder viewHolder, int itemType, T t);

}
