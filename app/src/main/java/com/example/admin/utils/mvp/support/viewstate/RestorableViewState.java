package com.example.admin.utils.mvp.support.viewstate;

import android.os.Bundle;
import android.view.View;

import com.example.admin.utils.mvp.MvpView;

/**
 * function：备忘录模式：抽象备忘录，定义保存、恢复功能
 * author by cpMark
 * create on 2018/5/27.
 */
public interface RestorableViewState<V extends MvpView> extends ViewState<V> {

    /**
     *  保存
     */
    void onSaveInstanceState(Bundle outState);

    /**
     *  恢复
     */
    void onRestoreInstanceState(Bundle savedInstanceState);

}
