package com.example.admin.utils.recycler_view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.example.admin.utils.recycler_view.holder.CommonViewHolder;
import com.example.admin.utils.recycler_view.support.MultiItemTypeSupport;
import com.example.admin.utils.recycler_view.support.SectionSupport;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * function：带头布局的RecyclerView适配器（多条目的一种）
 * author by admin
 * create on 2018/4/27.
 */
public abstract class SectionAdapter<T> extends MultiItemCommonAdapter<T> {

    /**
     *  头布局唯一标识
     */
    private static final int TYPE_SECTION = 0;
    private SectionSupport mSectionSupport;
    private LinkedHashMap<String, Integer> mSections;

    private MultiItemTypeSupport<T> mHeaderItemTypeSupport = new MultiItemTypeSupport<T>() {
        @Override
        public int getLayoutId(int itemType) {
            if (itemType == TYPE_SECTION)
                //头布局id
                return mSectionSupport.sectionHeaderLayoutId();
            else
                //正常的布局id
                return mLayoutId;
        }

        @Override
        public int getItemViewType(int position, T o) {
            //根据是否缓存该索引位置来确定是头布局，还是正常布局
            return mSections.values().contains(position) ? TYPE_SECTION : 1;
        }
    };

    public SectionAdapter(Context context, int layoutId, List<T> datas, SectionSupport sectionSupport) {
        super(context, datas, null);
        mLayoutId = layoutId;
        //多条目支持
        mMultiItemTypeSupport = mHeaderItemTypeSupport;
        //头布局支持
        mSectionSupport = sectionSupport;
        //头布局索引位置缓存集合
        mSections = new LinkedHashMap<>();
        findSections();
        //注册观察者，当数据发生变化时会重新查询头布局
        registerAdapterDataObserver(mObserver);
    }

    @Override
    public int getItemViewType(int position) {
        return mMultiItemTypeSupport.getItemViewType(position, null);
    }

    final RecyclerView.AdapterDataObserver mObserver = new RecyclerView.AdapterDataObserver() {
        @Override
        public void onChanged() {
            super.onChanged();
            //重新查询头布局
            findSections();
        }
    };

//    @Override
//    protected boolean isEnabled(int viewType) {
//        if (viewType == TYPE_SECTION)
//            return false;
//        return super.isEnabled(viewType);
//    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        //当数据不可见时，注销观察者
        unregisterAdapterDataObserver(mObserver);
    }

    /**
     *  初始化缓存集合
     */
    public void findSections() {
        int n = mDatasList.size();
        //含有不同头布局的个数
        int nSections = 0;
        mSections.clear();

        for (int i = 0; i < n; i++) {
            //头布局文本名称
            String sectionName = mSectionSupport.getTitle(mDatasList.get(i));

            //在还没有缓存过该头布局文本名称的情况下，缓存起来
            if (!mSections.containsKey(sectionName)) {
                //头布局的索引位置为当前position+已有头布局数
                mSections.put(sectionName, i + nSections);
                nSections++;
            }
        }

    }

    @Override
    public int getItemCount() {
        return super.getItemCount() + mSections.size();
    }

    /**
     *  重新计算位置索引
     */
    public int getIndexForPosition(int position) {
        int nSections = 0;

        Set<Map.Entry<String, Integer>> entrySet = mSections.entrySet();
        for (Map.Entry<String, Integer> entry : entrySet) {
            if (entry.getValue() < position) {
                nSections++;
            }
        }
        return position - nSections;
    }

    @Override
    public void onBindViewHolder(CommonViewHolder holder, int position) {
        position = getIndexForPosition(position);
        if (holder.getItemViewType() == TYPE_SECTION) {
            holder.setText(mSectionSupport.sectionTitleTextViewId(), mSectionSupport.getTitle(mDatasList.get(position)));
            return;
        }
        super.onBindViewHolder(holder, position);
    }

}
