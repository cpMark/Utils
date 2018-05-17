package com.example.admin.utils.mvp.lce.impl;

import android.view.View;

import com.example.admin.utils.R;
import com.example.admin.utils.mvp.MvpPresenter;
import com.example.admin.utils.mvp.MvpView;
import com.example.admin.utils.mvp.base.MvpBaseActivity;
import com.example.admin.utils.mvp.lce.ILceAnimator;
import com.example.admin.utils.mvp.lce.MvpLceView;

/**
 * function：Lce的代理对象（持有目标对象MvpLceViewImpl的引用）
 * author by admin
 * create on 2018/5/17.
 */
public class MvpLceActivity<D, V extends MvpView, P extends MvpPresenter<V>>
        extends MvpBaseActivity<V, P> implements MvpLceView<D>, View.OnClickListener {

    private MvpLceViewImpl<D> mMvpLceView;

    public MvpLceViewImpl<D> getMvpLceView() {
        if (mMvpLceView == null) {
            mMvpLceView = new MvpLceViewImpl<>();
        }

        return mMvpLceView;
    }

    /**
     * 加载Lce布局
     */
    @Override
    public void onContentChanged() {
        super.onContentChanged();
        getMvpLceView().initView(findViewById(R.id.rootView));
        getMvpLceView().setOnClickErrorListener(this);
    }

    @Override
    public void onClick(View view) {
        //重新加载
        loadData(false);
    }

    /**
     * 设置lce动画，不设置会有默认效果
     *
     * @param lceAnimator lce动画策略接口的实现类
     */
    public void setAnimator(ILceAnimator lceAnimator) {
        getMvpLceView().setAnimator(lceAnimator);
    }

    @Override
    public void loadData(boolean isPullToRefresh) {
        getMvpLceView().loadData(isPullToRefresh);
    }

    @Override
    public void showLoading(boolean isPullToRefresh) {
        getMvpLceView().showLoading(isPullToRefresh);
    }

    @Override
    public void showContent(boolean isPullToRefresh) {
        getMvpLceView().showContent(isPullToRefresh);
    }

    @Override
    public void bindData(D data, boolean isPullToRefresh) {
        getMvpLceView().bindData(data, isPullToRefresh);
    }

    @Override
    public void showError(boolean isPullToRefresh) {
        getMvpLceView().showError(isPullToRefresh);
    }
}
