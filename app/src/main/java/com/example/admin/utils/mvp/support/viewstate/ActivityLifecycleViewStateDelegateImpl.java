package com.example.admin.utils.mvp.support.viewstate;

import android.app.Activity;
import android.os.Bundle;

import com.example.admin.utils.mvp.MvpPresenter;
import com.example.admin.utils.mvp.MvpView;
import com.example.admin.utils.mvp.support.cache.PresenterManager;
import com.example.admin.utils.mvp.support.lifecycle.activity.ActivityLifecycleDelegateImpl;
import com.example.admin.utils.mvp.support.lifecycle.common.BindAndUnbindDelegate;
import com.example.admin.utils.network.utils.ExceptionsUtils;

/**
 * function：针对生命周期 -> 扩展目标对象（是在原生生命周期目标对象的基础上扩展了缓存数据的目标对象）
 * author by cpMark
 * create on 2018/5/24.
 */
public class ActivityLifecycleViewStateDelegateImpl<V extends MvpView, P extends MvpPresenter<V>, VS extends ViewState<V>>
        extends ActivityLifecycleDelegateImpl<V,P> {

    /**
     *  ViewState缓存代理模式目标对象的应用
     */
    private MvpViewStateDelegate<V,P,VS> mMvpViewStateDelegate;

    public ActivityLifecycleViewStateDelegateImpl(Activity activity, MvpViewStateDelegate<V,P,VS> delegate, boolean keepPresenterInstance) {
        super(activity, delegate, keepPresenterInstance);
        mMvpViewStateDelegate = delegate;
    }


    private void setViewState(VS viewState,boolean applyViewState,boolean applyViewStateFromMemory){
        if(viewState == null){
            ExceptionsUtils.nullPoint("viewState can not be null!");
        }

        mMvpViewStateDelegate.setViewState(viewState);

        if(applyViewState){
            //设置当前状态为恢复中
            mMvpViewStateDelegate.setRestoringViewState(true);
            //调用具体的备份方法（由子类决定）
            mMvpViewStateDelegate.getViewState().apply(mMvpViewStateDelegate.getMvpView(),applyViewStateFromMemory);
            //设置当前状态为非恢复中
            mMvpViewStateDelegate.setRestoringViewState(false);
            mMvpViewStateDelegate.onViewStateInstanceRestored(applyViewStateFromMemory);
        }
    }

    /** --------------------------------------------  重写扩展的数据缓存生命周期方法start  --------------------------------------------*/

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        VS viewState = null;
        if(mActivityId != null){
            viewState = PresenterManager.getViewState(mActivity,mActivityId);
            if(viewState != null){
                //缓存
                setViewState(viewState,true,true);
                return ;
            }
        }

        //如果ViewState为空创建一个ViewState
        viewState = mMvpViewStateDelegate.createViewState();
        if(viewState == null){
            ExceptionsUtils.nullPoint("You must create instance of viewState!!!");
        }

        if(savedInstanceState != null && viewState instanceof RestorableViewState){
            RestorableViewState restorableViewState =  (RestorableViewState) viewState;
            restorableViewState.onRestoreInstanceState(savedInstanceState);

            if(restorableViewState != null){
                viewState = (VS)restorableViewState;
                setViewState(viewState,true,false);
                if(mKeepPresenterInstance){
                    if(mActivityId == null){
                        ExceptionsUtils.nullPoint("ActivityId can not be null!!!");
                    }
                    PresenterManager.putViewState(mActivity,mActivityId,viewState);
                }

                return ;
            }
        }

        if(mKeepPresenterInstance){
            if(mActivityId == null){
                ExceptionsUtils.nullPoint("ActivityId can not be null!!!");
            }

            PresenterManager.putViewState(mActivity,mActivityId,viewState);
        }

        //恢复完毕
        setViewState(viewState,false,false);

        //创建新的缓存实例对象为下一次备份使用
        mMvpViewStateDelegate.onNewViewStateInstance();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        boolean keepInstance = retainPresenterInstance(mKeepPresenterInstance, mActivity);
        VS viewState = mMvpViewStateDelegate.getViewState();
        if(viewState == null){
            ExceptionsUtils.nullPoint("Viewstate can not be null!!!");
        }

        //存在P层，并且ViewState是恢复ViewState的实例，那么需要缓存
        if(keepInstance && viewState instanceof RestorableViewState){
            ((RestorableViewState)viewState).onSaveInstanceState(outState);
        }
    }

    /** --------------------------------------------  重写扩展的数据缓存生命周期方法end  --------------------------------------------*/

}
