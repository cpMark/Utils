package com.example.admin.utils.recycler_view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.dz3688.www.util.recycler_view.holder.CommonViewHolder;

import java.util.List;

/**
 * function：RecyclerView通用Adapter（针对单条目）
 * author by admin
 * create on 2018/4/27.
 */

public abstract class CommonAdapter<T> extends RecyclerView.Adapter<CommonViewHolder> {

    protected Context mContext;

    /**
     * item布局文件
     */
    protected int mLayoutId;
    protected List<T> mDatasList;
    protected LayoutInflater mInflater;


    public CommonAdapter(Context context, int layoutId, List<T> dataList) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mLayoutId = layoutId;
        mDatasList = dataList;
    }

    @Override
    public CommonViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        CommonViewHolder viewHolder = CommonViewHolder.get(mContext, parent, mLayoutId);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CommonViewHolder holder, int position) {
        convert(holder, mDatasList.get(position));
    }

    public abstract void convert(CommonViewHolder holder, T t);

    @Override
    public int getItemCount() {
        return mDatasList.size();
    }
}
