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


    /**
     *  生命周期绑定代理对象引用声明
     */
    private BindAndUnbindDelegateProxy<V, P> mBindAndUnbindDelegateProxy;

    /**
     * 是否保存Presenter实例的标识
     */
    protected boolean mKeepPresenterInstance;

    /**
     *  对应的Activity应用
     */
    protected Activity mActivity;

    /**
     *  Activity的唯一标识
     */
    protected String mActivityId;

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

            //mActivityId不为null,说明有缓存了，不做任何处理
            if(mActivityId == null){
                //初始化缓存
                presenter = initPresenterCache();
            }

        }else{
            //没有缓存，直接调用函数创建即可，并根据初始化标识mKeepPresenterInstance决定是否缓存
            presenter = initPresenterCache();
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
        if(retainPresenterInstance){
            mBindAndUnbindDelegateProxy.getPresenter().destory();
        }

        //存在实例，并且id不为空，需要销毁实例
        if (!retainPresenterInstance && mActivityId != null){
            PresenterManager.remove(mActivity, mActivityId);
        }

    }

    /**
     * Presenter实例和Activity实例是否都存在？
     * (不存在返回false，没必要销毁；存在返回true，需要销毁)
     */
    protected boolean retainPresenterInstance(boolean keepPresenterInstance, Activity activity){
        return keepPresenterInstance &&
                (activity.isChangingConfigurations() || !activity.isFinishing());
    }

    /**
     *  初始化Presenter缓存
     */
    private P initPresenterCache(){
        P presenter = createPresenterInstance();
        addPresenterToCache(presenter);
        return presenter;
    }

    /**
     *  添加Presenter实例到缓存中
     */
    private void addPresenterToCache(P presenter) {
        if(mKeepPresenterInstance){
            //通过PresenterManager来初始化缓存
            mActivityId = UUID.randomUUID().toString();
            PresenterManager.putPresenter(mActivity, mActivityId,presenter);
        }
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

    /** --------------------------------------------  数据缓存声明周期方法  --------------------------------------------*/

    @Override
    public void onSaveInstanceState(Bundle outState) {

    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {

    }
}
