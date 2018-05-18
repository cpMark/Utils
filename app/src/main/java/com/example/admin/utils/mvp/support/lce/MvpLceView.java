package com.example.admin.utils.mvp.support.lce;

import com.example.admin.utils.mvp.MvpView;

/**
 * function：Lce的目标接口（也是Lce代理模式的目标接口）
 * author by admin
 * create on 2018/5/17.
 */
public interface MvpLceView<D> extends MvpView {

    /**
     * 1、加载数据
     *
     * @param isPullToRefresh->是否是下拉刷新组件
     */
    void loadData(boolean isPullToRefresh);

    /**
     * 2、显示加载页面
     *
     * @param isPullToRefresh
     */
    void showLoading(boolean isPullToRefresh);

    /**
     * 3、成功->更新UI层（显示内容View）
     *
     * @param isPullToRefresh
     */
    void showContent(boolean isPullToRefresh);

    /**
     * 4、绑定数据
     *
     * @param isPullToRefresh
     */
    void bindData(D data, boolean isPullToRefresh);

    /**
     * 5、失败->显示错误页面
     *
     * @param isPullToRefresh
     */
    void showError(boolean isPullToRefresh);

}
