package com.example.admin.utils.mvp.support.lifecycle.activity;

import android.os.Bundle;

import com.example.admin.utils.mvp.MvpPresenter;
import com.example.admin.utils.mvp.MvpView;

/**
 * function：Activity生命周期的目标接口
 * author by admin
 * create on 2018/5/17.
 */
public interface ActivityLifecycleDelegate<V extends MvpView,P extends MvpPresenter<V>> {

    void onCreate(Bundle savedInstanceState);

    void onStart();

    void onRestart();

    void onResume();

    void onPause();

    void onStop();

    void onDestroy();
}
