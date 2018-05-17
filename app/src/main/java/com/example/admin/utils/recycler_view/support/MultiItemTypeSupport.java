package com.example.admin.utils.recycler_view.support;

/**
 * function：多Item支持接口
 * author by admin
 * create on 2018/4/27.
 */
public interface MultiItemTypeSupport<T> {

    /**
     *  根据item的类型加载布局
     */
    int getLayoutId(int itemType);

    /**
     *  根据位置索引和数据泛型Bean，来确定item的类型
     */
    int getItemViewType(int position, T t);
}
