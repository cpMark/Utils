package com.example.admin.utils.mvp.support.lce.common.animator;

import android.view.View;

import com.example.admin.utils.mvp.support.lce.ILceAnimator;

/**
 * function：Lce策略接口的默认实现类，当用户没有传入自定义策略时使用
 * author by admin
 * create on 2018/5/17.
 */
public class DefaultLceAnimator implements ILceAnimator {

    @Override
    public void showLoadingView(View loadingView, View contentView, View errorView) {
        AnimatorUtils.getInstance().showLoading(loadingView, contentView, errorView);
    }

    @Override
    public void showContentView(View loadingView, View contentView, View errorView) {
        AnimatorUtils.getInstance().showContent(loadingView, contentView, errorView);
    }

    @Override
    public void showErrorView(View loadingView, View contentView, View errorView) {
        AnimatorUtils.getInstance().showErrorView(loadingView, contentView, errorView);
    }
}
