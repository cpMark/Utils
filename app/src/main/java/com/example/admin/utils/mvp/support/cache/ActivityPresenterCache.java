package com.example.admin.utils.mvp.support.cache;

import android.annotation.TargetApi;
import android.os.Build;
import android.util.ArrayMap;

import com.example.admin.utils.mvp.MvpPresenter;
import com.example.admin.utils.mvp.support.viewstate.ViewState;
import com.example.admin.utils.network.utils.ExceptionsUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * function：缓存Activity中的Presenter
 * author by admin
 * create on 2018/5/18.
 */
public class ActivityPresenterCache {

    /**
     * Presenter的缓存集合，key为activityId，value是Presenter对象的一个包装类
     */
    private final Map<String, PresenterHolder> mPresenterCacheMap = new HashMap();

    ActivityPresenterCache() {

    }

    /**
     * 根据activityId获取缓存P
     */
    public <P> P getPresenter(String activityId) {
        PresenterHolder presenterHolder = mPresenterCacheMap.get(activityId);
        return presenterHolder == null ? null : (P) presenterHolder.mPresenter;
    }

    /**
     * 添加缓存P
     */
    public void putPresenter(String activityId, MvpPresenter<?> presenter) {
        if (activityId == null) {
            ExceptionsUtils.nullPoint("ActivityId is null,please check!!!");
        }

        if (presenter == null) {
            ExceptionsUtils.nullPoint("The presenter instance of cache is null,please check!!!");
        }

        PresenterHolder presenterHolder = mPresenterCacheMap.get(activityId);
        if (presenterHolder == null) {
            presenterHolder = new PresenterHolder();
            presenterHolder.mPresenter = presenter;
            mPresenterCacheMap.put(activityId, presenterHolder);
        } else {
            presenterHolder.mPresenter = presenter;
        }

    }

    /**
     * 移除和activityId对应的P层缓存
     */
    public void remove(String activityId) {
        
        if (activityId == null) {
            ExceptionsUtils.nullPoint("ActivityId is null,please check!!!");
        }

        mPresenterCacheMap.remove(activityId);
    }

    public void clear() {
        mPresenterCacheMap.clear();
    }

    static final class PresenterHolder {
        private MvpPresenter<?> mPresenter;
        private Object mViewState;
    }

    /**
     * --------------------------------------------  ViewState相关的函数 start  --------------------------------------------
     */

    public <VS> VS getViewState(String activityId) {
        PresenterHolder presenterHolder = mPresenterCacheMap.get(activityId);
        return presenterHolder == null ? null : (VS) presenterHolder.mViewState;
    }

    public void putViewState(String activityId, Object viewState) {
        if (viewState == null){
            ExceptionsUtils.nullPoint("viewState不能为空");
        }
        if (activityId == null){
            ExceptionsUtils.nullPoint("viewId不能为空");
        }

        PresenterHolder presenterHolder = mPresenterCacheMap.get(activityId);
        if(presenterHolder == null){
            presenterHolder = new PresenterHolder();
            presenterHolder.mViewState = viewState;
            mPresenterCacheMap.put(activityId,presenterHolder);
        }else{
            presenterHolder.mViewState = viewState;
        }
    }

    /** --------------------------------------------  ViewState相关的函数 end  --------------------------------------------*/

}
