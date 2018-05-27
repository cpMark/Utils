package com.example.admin.utils.mvp.support.viewstate;

import com.example.admin.utils.mvp.MvpView;

/**
 * function：备忘录模式：抽象备忘录，定义备份功能
 * author by cpMark
 * create on 2018/5/27.
 */
public interface ViewState<V extends MvpView> {

    /**
     *  备忘
     *  @param view 缓存的View
     *  @param retained
     */
    void apply(V view,boolean retained);

}
