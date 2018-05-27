package com.example.admin.utils.mvp.support.viewstate;

import com.example.admin.utils.mvp.MvpPresenter;
import com.example.admin.utils.mvp.MvpView;
import com.example.admin.utils.mvp.support.lifecycle.common.BindAndUnbindDelegate;

/**
 * function：针对ViewState缓存->目标接口
 * author by cpMark
 * create on 2018/5/27.
 */
public interface MvpViewStateDelegate<V extends MvpView, P extends MvpPresenter<V>, VS extends ViewState<V>>
        extends BindAndUnbindDelegate<V, P> {

    VS getViewState();

    void setViewState(VS viewState);

    VS createViewState();

    /**
     *  设置当前的恢复状态
     *  @param isRestoring true标识当前正在恢复
     */
    void setRestoringViewState(boolean isRestoring);

    boolean isRestoring();

    void onViewStateInstanceRestored(boolean instanceRetained);

    void onNewViewStateInstance();

}
