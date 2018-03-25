package com.example.admin.view.adapter.base;


import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.util.SparseArray;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by xyp on 2018/3/18.
 * 多布局的通用适配器
 */

public abstract class MultiItemTypeAdapter<T> extends RecyclerView.Adapter {

    private List<T> data;//数据源

    public MultiItemTypeAdapter(List<T> data) {
        if (data == null) throw new NullPointerException("data is null");
        this.data = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return createHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int itemType = getItemType(position);
        int realPosition = getRealPotion(position);
        bindHolder((CommonViewHolder) holder, itemType, realPosition < 0 ? null : data.get(realPosition));
    }

    @Override
    public int getItemCount() {
        return data.size() + getTypeCount();
    }

    @Override
    public int getItemViewType(int position) {
        return getItemType(position);
    }


    @Override
    public void onViewAttachedToWindow(@NonNull RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        if (isFullSpan(holder.getLayoutPosition())) {
            ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
            if (lp != null && lp instanceof StaggeredGridLayoutManager.LayoutParams) {
                StaggeredGridLayoutManager.LayoutParams params = (StaggeredGridLayoutManager.LayoutParams) lp;
                params.setFullSpan(true);
            }
        }
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager lm = recyclerView.getLayoutManager();
        if (lm instanceof GridLayoutManager) {
            final GridLayoutManager layoutManager = (GridLayoutManager) lm;
            layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    if (isFullSpan(position))
                        return layoutManager.getSpanCount();//这一行的spanCount列都被它占用
                    return 1;
                }
            });
        }
    }


    //根据情况重写是否是多布局
    public abstract int getItemType(int position);

    //根据情况重写ViewHolder的创建
    public abstract CommonViewHolder createHolder(ViewGroup parent, int viewType);

    //根据情况重写布局个数,这个count是去掉真实数据源的其他布局的数量,比如除了正常内容还有头布局与尾布局
    public abstract int getTypeCount();

    //根据情况绑定数据,这个position要注意多布局情况下
    public abstract void bindHolder(CommonViewHolder viewHolder, int itemType, T t);

    //返回去掉其他非数据源布局type的position
    public abstract int getRealPotion(int position);

    //在StaggeredGridLayoutManager或GridLayoutManager模式下判断是否独占一行
    public abstract boolean isFullSpan(int position);
}
