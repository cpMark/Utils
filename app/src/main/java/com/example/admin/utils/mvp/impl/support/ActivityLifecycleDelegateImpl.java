package com.example.admin.utils.mvp.impl.support;

import android.os.Bundle;

import com.example.admin.utils.mvp.MvpPresenter;
import com.example.admin.utils.mvp.MvpView;

/**
 * function：
 * （1）Activity生命周期的目标对象（目标接口实现类）
 * （2）V层和P层绑定、解绑的
 * author by admin
 * create on 2018/5/17.
 */
public class ActivityLifecycleDelegateImpl<V extends MvpView,P extends MvpPresenter<V>> implements ActivityLifecycleDelegate<V,P> {

    private BindAndUnbindDelegateProxy<V,P> mBindAndUnbindDelegateProxy;

    public ActivityLifecycleDelegateImpl(BindAndUnbindDelegate delegate){
        mBindAndUnbindDelegateProxy = new BindAndUnbindDelegateProxy<V,P>(delegate);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        mBindAndUnbindDelegateProxy.createPresenter();
        mBindAndUnbindDelegateProxy.attachView();
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onRestart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {
        mBindAndUnbindDelegateProxy.detachView();
    }
}
