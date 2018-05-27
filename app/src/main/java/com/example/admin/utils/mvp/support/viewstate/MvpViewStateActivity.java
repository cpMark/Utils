package com.example.admin.utils.mvp.support.viewstate;

import com.example.admin.utils.mvp.MvpPresenter;
import com.example.admin.utils.mvp.MvpView;
import com.example.admin.utils.mvp.support.lifecycle.activity.ActivityLifecycleDelegate;
import com.example.admin.utils.mvp.support.lifecycle.activity.MvpActivity;

/**
 * function：
 * <p>
 * 代理一：针对生命周期 -> 扩展代理代理对象（持有目标对象引用）
 * 代理二：针对ViewState缓存 -> 目标对象
 * <p>
 * 备忘录模式 -> 创建备忘录角色
 * <p>
 * <p>
 * author by cpMark
 * create on 2018/5/27.
 */
public abstract class MvpViewStateActivity<V extends MvpView, P extends MvpPresenter<V>, VS extends ViewState<V>>
        extends MvpActivity<V, P>
        implements MvpViewStateDelegate<V, P, VS> {

    /**
     *  这里持有第一重代理的目标对象
     */
    @Override
    public ActivityLifecycleDelegate<V, P> getActivityLifecycleDelegate() {
        //当生命周期目标对象为null时，创建扩展生命周期代理的目标对象
        if (mActivityLifecycleDelegate == null) {
            mActivityLifecycleDelegate = new ActivityLifecycleViewStateDelegateImpl<>(this,this,true);
        }
        //此时返回的就是扩展了功能的目标对象
        return super.getActivityLifecycleDelegate();
    }

    /** --------------------------------------------  针对ViewState缓存的函数重写 start  --------------------------------------------*/

    /**
     *  状态缓存对象
     */
    private VS mViewState;

    /**
     *  是否已经恢复的状态标识
     */
    protected boolean mIsRestoring = false;

    @Override
    public VS getViewState() {
        return mViewState;
    }

    @Override
    public void setViewState(VS viewState) {
        mViewState = viewState;
    }

    @Override
    public VS createViewState() {
        return null;
    }

    @Override
    public void setRestoringViewState(boolean isRestoring) {
        mIsRestoring = isRestoring;
    }

    @Override
    public boolean isRestoring() {
        return mIsRestoring;
    }

    @Override
    public void onViewStateInstanceRestored(boolean instanceRetained) {

    }

    /** --------------------------------------------  针对ViewState缓存的函数重写 end  --------------------------------------------*/
}
