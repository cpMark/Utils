package com.example.admin.utils.recycler_view.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.dz3688.www.util.recycler_view.holder.CommonViewHolder;
import com.dz3688.www.util.recycler_view.support.MultiItemTypeSupport;

import java.util.List;

/**
 * function：多条目类型的RecyclerView通用适配器
 * author by admin
 * create on 2018/4/27.
 */
public abstract class MultiItemCommonAdapter<T> extends CommonAdapter<T> {

    /**
     * 桥接模式：
     * 将定义有根据不同位置索引和数据Bean类来确定itemType，和通过不同itemType确定不同布局id的逻辑接入
     */
    protected MultiItemTypeSupport<T> mMultiItemTypeSupport;

    public MultiItemCommonAdapter(Context context, List<T> datas,
                                  MultiItemTypeSupport<T> multiItemTypeSupport) {
        super(context, -1, datas);
        mMultiItemTypeSupport = multiItemTypeSupport;
    }

    @Override
    public int getItemViewType(int position) {
        return mMultiItemTypeSupport.getItemViewType(position, mDatasList.get(position));
    }

    @Override
    public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutId = mMultiItemTypeSupport.getLayoutId(viewType);
        CommonViewHolder holder = CommonViewHolder.get(mContext, parent, layoutId);
        return holder;
    }
}
