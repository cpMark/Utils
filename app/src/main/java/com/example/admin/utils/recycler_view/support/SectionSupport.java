package com.example.admin.utils.recycler_view.support;

/**
 * function：RecyclerView头布局的添加
 * author by admin
 * create on 2018/4/27.
 */
public interface SectionSupport<T> {

    /**
     *  头布局id
     */
    int sectionHeaderLayoutId();

    /**
     *  头布局的文本TextView的id
     */
    int sectionTitleTextViewId();

    /**
     * 泛型T对应数据类型Bean
     */
    String getTitle(T t);
}
