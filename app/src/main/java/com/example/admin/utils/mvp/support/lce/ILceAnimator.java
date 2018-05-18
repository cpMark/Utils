package com.example.admin.utils.mvp.support.lce;

import android.view.View;

/**
 * function：动画的策略接口 -- Loading、Content、Error
 * author by admin
 * create on 2018/5/17.
 */
public interface ILceAnimator {

    /**
     * 加载页面
     * @param loadingView
     * @param contentView
     * @param errorView
     */
    void showLoadingView(View loadingView, View contentView, View errorView);

    /**
     * 内容页面
     * @param loadingView
     * @param contentView
     * @param errorView
     */
    void showContentView(View loadingView, View contentView, View errorView);

    /**
     * 错误页面
     * @param loadingView
     * @param contentView
     * @param errorView
     */
    void showErrorView(View loadingView, View contentView, View errorView);

}
