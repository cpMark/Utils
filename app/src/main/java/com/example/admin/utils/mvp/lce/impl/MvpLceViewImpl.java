package com.example.admin.utils.mvp.lce.impl;

import android.view.View;

import com.example.admin.utils.R;
import com.example.admin.utils.mvp.lce.ILceAnimator;
import com.example.admin.utils.mvp.lce.MvpLceView;
import com.example.admin.utils.mvp.lce.impl.animator.DefaultLceAnimator;

/**
 * function：Lce的目标对象（MvpLceView的实现类）
 * author by admin
 * create on 2018/5/17.
 */
public class MvpLceViewImpl<D> implements MvpLceView<D> {

    private ILceAnimator mAnimator;

    public void setAnimator(ILceAnimator animator){
        mAnimator = animator;
    }

    /**
     *  获取动画策略的实现类对象，如果为null，则实例化我们的默认实现类
     */
    public ILceAnimator getAnimator(){
        if(mAnimator == null){
            mAnimator = new DefaultLceAnimator();
        }

        return mAnimator;
    }

    //加载View
    private View mContentView;
    private View mLoadingView;
    private View mErrorView;

    public void initView(View rootView){
        if (rootView == null){
            throw new NullPointerException("root view 不能给为空");
        }
        if (mLoadingView == null){
            this.mLoadingView = rootView.findViewById(R.id.loadingView);
        }
        if (mContentView == null){
            this.mContentView = rootView.findViewById(R.id.contentView);
        }
        if (mErrorView == null){
            this.mErrorView = rootView.findViewById(R.id.errorView);
        }
        if (mLoadingView == null){
            throw new NullPointerException("mLoadingView 不能给为空");
        }
        if (mContentView == null){
            throw new NullPointerException("mContentView 不能给为空");
        }
        if (mErrorView == null){
            throw new NullPointerException("mErrorView 不能给为空");
        }
    }

    public void setOnClickErrorListener(View.OnClickListener onClickListener){
        if (mErrorView != null){
            mErrorView.setOnClickListener(onClickListener);
        }
    }

    @Override
    public void loadData(boolean isPullToRefresh) {

    }

    @Override
    public void bindData(D data, boolean isPullToRefresh) {

    }

    @Override
    public void showLoading(boolean isPullToRefresh) {
        if(!isPullToRefresh){
            getAnimator().showLoadingView(mLoadingView,mContentView,mErrorView);
        }
    }

    @Override
    public void showContent(boolean isPullToRefresh) {
        if(!isPullToRefresh){
            getAnimator().showContentView(mLoadingView,mContentView,mErrorView);
        }
    }


    @Override
    public void showError(boolean isPullToRefresh) {
        if(!isPullToRefresh){
            getAnimator().showErrorView(mLoadingView,mContentView,mErrorView);
        }
    }
}
