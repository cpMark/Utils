package com.example.admin.utils.mvp.support.base.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.admin.utils.mvp.MvpPresenter;
import com.example.admin.utils.mvp.MvpView;
import com.example.admin.utils.mvp.support.lifecycle.activity.MvpActivity;

/**
 * function：Activity的基本实现类，除了生命周期和绑定、解绑操作，没有其他多余功能
 * author by admin
 * create on 2018/5/18.
 */
public abstract class MvpBaseActivity<V extends MvpView, P extends MvpPresenter<V>> extends MvpActivity<V, P> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(createActivityResourceId());
        initView();
        initData();
        initListener();
    }

    /**
     * 初始化视图，可以根据需要重写
     */
    public void initView() {

    }

    /**
     * 初始化数据，可以根据需要重写
     */
    public void initData() {

    }

    /**
     * 初始化监听器，可以根据需要重写
     */
    public void initListener() {

    }


    /**
     * 创建Activity的xml资源id
     */
    protected abstract int createActivityResourceId();
}
