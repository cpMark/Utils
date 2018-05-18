package com.example.admin.utils.mvp.support.lifecycle;

import com.example.admin.utils.mvp.MvpPresenter;
import com.example.admin.utils.mvp.MvpView;

/**
 * function：抽象P层的实现基类，其它的基类继承它即可，这样就不用重复写这一段逻辑
 * author by admin
 * create on 2018/5/17.
 */
public class MvpBasePresenter<V extends MvpView> implements MvpPresenter<V> {

    V mView;

    public V getMvpView(){
        return mView;
    }

    @Override
    public void attachView(V view){
        mView = view;
    }

    @Override
    public void detachView(){
        mView = null;
    }

    @Override
    public void destory() {

    }
}
