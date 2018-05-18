package com.example.admin.utils.mvp.support.cache;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;
import android.os.Bundle;
import android.util.ArrayMap;

import com.example.admin.utils.mvp.MvpPresenter;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * function：Presenter管理器
 * author by admin
 * create on 2018/5/18.
 */
public class PresenterManager {

    /**
     * Activity唯一标识的key
     */
    static final String KEY_ACTIVITY_ID = "key_activity_id";

    /**
     * Activity唯一标识的缓存集合
     */
    private static final Map<Activity, String> mActivityIdCacheMap = new HashMap();

    /**
     * Activity中Presenter对象的缓存集合
     */
    private static final Map<String, ActivityPresenterCache> mRealPresenterCacheMap = new HashMap();

    /**
     * 全局的Activity生命周期监听器
     */
    static final Application.ActivityLifecycleCallbacks sCallbacks = new Application.ActivityLifecycleCallbacks() {

        /**
         *  对应Activity的onCreate方法，下面每一个方法都对应一个生命周期函数
         */
        @Override
        public void onActivityCreated(Activity activity, Bundle bundle) {
            if (bundle != null) {
                //此时说明有缓存数据
                String activityId = bundle.getString(KEY_ACTIVITY_ID);
                if (activityId != null) {
                    //缓存
                    mActivityIdCacheMap.put(activity, activityId);
                }
            }
        }

        @Override
        public void onActivityStarted(Activity activity) {

        }

        @Override
        public void onActivityResumed(Activity activity) {

        }

        @Override
        public void onActivityPaused(Activity activity) {

        }

        @Override
        public void onActivityStopped(Activity activity) {

        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
            //意外销毁时会调用此方法，在这里要作数据缓存
            String activityId = mActivityIdCacheMap.get(activity);
            if (activityId != null) {
                //缓存数据
                bundle.putString(KEY_ACTIVITY_ID, activityId);
            }
        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            //释放对象
            if (!activity.isChangingConfigurations()) {
                //当activity是正常finish时，要释放缓存的对象
                String activityId = mActivityIdCacheMap.get(activity);
                if (activityId != null) {
                    ActivityPresenterCache activityPresenterCache = mRealPresenterCacheMap.get(activityId);
                    if (activityPresenterCache != null) {
                        //清空数据
                        activityPresenterCache.clear();
                        mRealPresenterCacheMap.remove(activityId);
                    }

                    //移除之后缓存集合空了，就注销监听器
                    if (mRealPresenterCacheMap.isEmpty()) {
                        activity.getApplication().unregisterActivityLifecycleCallbacks(sCallbacks);
                    }
                }
            }

            mActivityIdCacheMap.remove(activity);
        }
    };

    /**
     * 规定PresenterManager类中都是类方法，不能存在实例方法
     */
    private PresenterManager() {
        throw new RuntimeException("This class can not create instance by constructor!!!");
    }

    /**
     * 通过Activity实例获取缓存实例
     */
    static ActivityPresenterCache getActivityPresenterCache(Activity activity) {
        if (activity == null) {
            throw new NullPointerException("Activity is null,please check!!!");
        }

        String activityId = mActivityIdCacheMap.get(activity);
        if (activityId == null) {
            activityId = UUID.randomUUID().toString();
            mActivityIdCacheMap.put(activity, activityId);

            //当缓存集合的size为1，说明开始有缓存数据了，我们要注册回调了
            if (mActivityIdCacheMap.size() == 1) {
                activity.getApplication().registerActivityLifecycleCallbacks(sCallbacks);
            }
        }

        ActivityPresenterCache activityPresenterCache = mRealPresenterCacheMap.get(activityId);
        if (activityPresenterCache == null) {
            activityPresenterCache = new ActivityPresenterCache();
            mRealPresenterCacheMap.put(activityId, activityPresenterCache);
        }

        return activityPresenterCache;
    }

    /**
     * 通过Activity实例和其id获取对应的Presenter实例
     */
    public static <P> P getPresenter(Activity activity, String activityId) {
        if (activity == null) {
            throw new NullPointerException("Activity is null,please check!!!");
        }

        if (activityId == null) {
            throw new NullPointerException("ActivityId is null,please check!!!");
        }

        ActivityPresenterCache activityPresenterCache = getActivityPresenterCache(activity);
        return activityPresenterCache == null ? null : (P) activityPresenterCache.getPresenter(activityId);
    }

    /**
     * 获取Activity
     */
    public static Activity getActivity(Context context) {
        if (context == null) {
            throw new NullPointerException("Context instance is null,please check!!!");
        }

        if (context instanceof Activity) {
            return (Activity) context;
        }

        //循环获取父类类型
        while (context instanceof ContextWrapper) {
            if (context instanceof Activity) {
                return (Activity) context;
            }
            context = ((ContextWrapper) context).getBaseContext();
        }

        //抛出异常
        throw new IllegalStateException("Do not find corresponding the instance of activity!!!");
    }

    /**
     * 重置集合操作
     */
    static void reset() {
        mActivityIdCacheMap.clear();
        for (ActivityPresenterCache activityPresenterCache : mRealPresenterCacheMap.values()) {
            activityPresenterCache.clear();
        }

        mRealPresenterCacheMap.clear();
    }

    /**
     *  将Presenter添加到对应Activity的缓存中
     */
    public static void putPresenter(Activity activity, String activityId, MvpPresenter<?> presenter){
        if (activity == null) {
            throw new NullPointerException("Activity is null,please check!!!");
        }

        //通过activity，获取其缓存实例
        ActivityPresenterCache activityPresenterCache = getActivityPresenterCache(activity);
        activityPresenterCache.putPresenter(activityId,presenter);
    }

    /**
     *  移除对应Activity的缓存
     */
    public static void remove(Activity activity,String activityId){
        if (activity == null) {
            throw new NullPointerException("Activity is null,please check!!!");
        }

        ActivityPresenterCache activityPresenterCache = getActivityPresenterCache(activity);
        activityPresenterCache.remove(activityId);
    }

}
