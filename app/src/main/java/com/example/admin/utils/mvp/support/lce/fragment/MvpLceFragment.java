package com.example.admin.utils.mvp.support.lce.fragment;

import android.os.Bundle;
import android.view.View;

import com.example.admin.utils.R;
import com.example.admin.utils.mvp.MvpPresenter;
import com.example.admin.utils.mvp.MvpView;
import com.example.admin.utils.mvp.support.lce.MvpLceView;
import com.example.admin.utils.mvp.support.lce.common.MvpLceViewImpl;
import com.example.admin.utils.mvp.support.lifecycle.fragment.MvpFragment;

/**
 * function：Lce的代理对象（持有目标对象MvpLceViewImpl的引用）
 * author by admin
 * create on 2018/5/18.
 */
public class MvpLceFragment<D, V extends MvpView, P extends MvpPresenter<V>>
        extends MvpFragment<V, P> implements MvpLceView<D>, View.OnClickListener{

    private MvpLceViewImpl<D> mMvpLceView;

    public MvpLceViewImpl<D> getMvpLceView() {
        if (mMvpLceView == null) {
            mMvpLceView = new MvpLceViewImpl<>();
        }

        return mMvpLceView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getMvpLceView().initView(view.findViewById(R.id.rootView));
        getMvpLceView().setOnClickErrorListener(this);
    }

    @Override
    public void onClick(View view) {
        loadData(false);
    }

    /** --------------------------------------------  Lce目标接口的实现（调用目标对象的实现）start  --------------------------------------------*/

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
        getMvpLceView().bindData(data,isPullToRefresh);
    }

    @Override
    public void showError(boolean isPullToRefresh) {
        getMvpLceView().showError(isPullToRefresh);
    }

    /** --------------------------------------------  Lce目标接口的实现（调用目标对象的实现）end  --------------------------------------------*/
}
