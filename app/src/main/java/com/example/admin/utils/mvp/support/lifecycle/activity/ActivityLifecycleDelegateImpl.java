package com.example.admin.utils.mvp.support.lifecycle.activity;

import android.app.Activity;
import android.os.Bundle;

import com.example.admin.utils.mvp.MvpPresenter;
import com.example.admin.utils.mvp.MvpView;
import com.example.admin.utils.mvp.support.cache.PresenterManager;
import com.example.admin.utils.mvp.support.lifecycle.common.BindAndUnbindDelegate;
import com.example.admin.utils.mvp.support.lifecycle.common.BindAndUnbindDelegateProxy;

import java.util.UUID;

/**
 * function：
 * （1）Activity生命周期的目标对象（目标接口实现类）
 * （2）V层和P层绑定、解绑的
 * author by admin
 * create on 2018/5/17.
 */
public class ActivityLifecycleDelegateImpl<V extends MvpView, P extends MvpPresenter<V>> implements ActivityLifecycleDelegate<V, P> {

    private static final String KEY_VIEW_ID = "key_view_id";

    private Activity mActivity;
    private BindAndUnbindDelegateProxy<V, P> mBindAndUnbindDelegateProxy;

    /**
     * 是否保存Presenter实例的标识
     */
    private boolean mKeepPresenterInstance;

    private String mActivityId;

    public ActivityLifecycleDelegateImpl(Activity activity, BindAndUnbindDelegate delegate, boolean keepPresenterInstance) {
        mActivity = activity;
        mBindAndUnbindDelegateProxy = new BindAndUnbindDelegateProxy<V, P>(delegate);
        mKeepPresenterInstance = keepPresenterInstance;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        P presenter = null;
        if(savedInstanceState != null && mKeepPresenterInstance){
            mActivityId = savedInstanceState.getString(KEY_VIEW_ID);

            if(mActivityId == null){
                //说明不存在，创建P层
                presenter = initPresenterCache();
            }
        }else{
            //不缓存，直接调用函数创建即可
            presenter = createPresenterInstance();
        }

        //绑定
        mBindAndUnbindDelegateProxy.setPresenter(presenter);
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

        boolean retainPresenterInstance = retainPresenterInstance(mKeepPresenterInstance, mActivity);
        mBindAndUnbindDelegateProxy.detachView();

        //P层根据标识，判断是否要销毁
        if(!retainPresenterInstance){
            mBindAndUnbindDelegateProxy.getPresenter().destory();
        }

        if (!retainPresenterInstance && mActivityId != null){
            PresenterManager.remove(mActivity, mActivityId);
        }

    }

    /**
     * 是否存在这个实例
     * (不存在返回false，存在返回true)
     */
    private boolean retainPresenterInstance(boolean keepPresenterInstance, Activity activity){
        return keepPresenterInstance &&
                (activity.isChangingConfigurations() || !activity.isFinishing());
    }

    /**
     *  初始化Presenter缓存
     */
    private P initPresenterCache(){
        P presenter = createPresenterInstance();

        if(mKeepPresenterInstance){
            //通过PresenterManager来初始化缓存
            mActivityId = UUID.randomUUID().toString();
            PresenterManager.putPresenter(mActivity, mActivityId,presenter);
        }

        return presenter;
    }

    /**
     *  创建Presenter实例
     */
    private P createPresenterInstance() {
        P presenter = mBindAndUnbindDelegateProxy.createPresenter();
        if (presenter == null){
            //如果为空，说明客户端实现类没有创建P对象
            throw new NullPointerException("Presenter is null,please check!!!");
        }
        return presenter;
    }
}
