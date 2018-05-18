package com.example.admin.utils.mvp.base.activity;

import android.app.Activity;
import android.os.Bundle;

import com.example.admin.utils.mvp.MvpPresenter;
import com.example.admin.utils.mvp.MvpView;
import com.example.admin.utils.mvp.base.common.BindAndUnbindDelegate;

/**
 * function：抽象Activity基类，实现了公共逻辑
 * <p>
 * Activity生命周期的代理对象（持有目标对象的引用）
 * V层和P层绑定的目标对象
 * <p>
 * author by admin
 * create on 2018/5/17.
 */
public class MvpBaseActivity<V extends MvpView, P extends MvpPresenter<V>>
        extends Activity
        implements MvpView,BindAndUnbindDelegate<V,P> {


    private ActivityLifecycleDelegate<V, P> mActivityLifecycleDelegate;

    /**
     * 通过使用时，不存在创建的方式来持有目标对象
     */
    public ActivityLifecycleDelegate<V, P> getActivityLifecycleDelegate() {
        if (mActivityLifecycleDelegate == null) {
            mActivityLifecycleDelegate = new ActivityLifecycleDelegateImpl(this);
        }
        return mActivityLifecycleDelegate;
    }

    /** --------------------------------------------  绑定和解绑接口实现start  --------------------------------------------*/

    private P mPresenter;

    @Override
    public P createPresenter() {
        return mPresenter;
    }

    @Override
    public P getPresenter() {
        return mPresenter;
    }

    @Override
    public void setPresenter(P presenter) {
        mPresenter = presenter;
    }

    @Override
    public V getMvpView() {
        return (V) this;
    }

    /** --------------------------------------------  绑定和解绑接口实现start  --------------------------------------------*/

    /** --------------------------------------------  生命周期回调start  --------------------------------------------*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivityLifecycleDelegate().onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        getActivityLifecycleDelegate().onStart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        getActivityLifecycleDelegate().onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getActivityLifecycleDelegate().onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        getActivityLifecycleDelegate().onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        getActivityLifecycleDelegate().onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getActivityLifecycleDelegate().onDestroy();
    }

    /** --------------------------------------------  生命周期回调start  --------------------------------------------*/

}
