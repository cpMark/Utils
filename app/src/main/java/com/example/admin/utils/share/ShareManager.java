package com.example.admin.utils.share;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.example.admin.utils.R;

/**
 * function：分享管理类
 * author by admin
 * create on 2018/4/28.
 */
public class ShareManager {

    private static volatile ShareManager sInstance;
    private Activity mActivity;
    private View mShareView;
    private PopupWindow mPopupWindow;

    private PopupWindow.OnDismissListener mDismissListener = new PopupWindow.OnDismissListener() {
        @Override
        public void onDismiss() {
            if (mActivity == null || mActivity.isFinishing()) {
                mActivity = null;
                return;
            }

            WindowManager.LayoutParams lp = mActivity.getWindow().getAttributes();
            lp.alpha = 1f;
            mActivity.getWindow().setAttributes(lp);
        }
    };

    private ShareManager() {
    }

    public static ShareManager getInstance() {
        if (sInstance == null) {
            synchronized (ShareManager.class) {
                if (sInstance == null) {
                    sInstance = new ShareManager();
                }
            }
        }

        return sInstance;
    }

    public void share(Activity activity, View locationView, View shareView, ShareBean shareBean) {
        if (activity == null || activity.isFinishing() || locationView == null || shareView == null || shareBean == null) {
            return;
        }

        //只分享地址，其它类型全部隐藏
        if(TextUtils.isEmpty(shareBean.getTargetUrl())){
            return ;
        }

        mActivity = activity;
        mShareView = shareView;
        mPopupWindow = null;

        if (mPopupWindow == null) {
            mPopupWindow = createPopupWindow();
        }

        ColorDrawable cd = new ColorDrawable(0x000000);
        WindowManager.LayoutParams lp = mActivity.getWindow().getAttributes();
        mActivity.getWindow().setAttributes(lp);
        mPopupWindow.setBackgroundDrawable(cd);
        mPopupWindow.showAtLocation(locationView, Gravity.BOTTOM, 0, 0);
    }

    private PopupWindow createPopupWindow() {
        PopupWindow popupWindow = new PopupWindow(mShareView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setAnimationStyle(R.style.PopupAnimation);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setOnDismissListener(mDismissListener);
        popupWindow.update();

        return popupWindow;
    }

    public void hide() {
        if (mPopupWindow != null && mPopupWindow.isShowing()) {
            mPopupWindow.dismiss();
        }
    }


    public void destroy() {
        hide();
        mPopupWindow = null;
    }
}
