package com.example.admin.utils.log;

import android.util.Log;

/**
 * function：日志打印类
 * author by admin
 * create on 2018/5/16.
 */
public class LogUtils {

    private static boolean mIsDebug = true;

    public static void init(boolean isDebug){
        mIsDebug = isDebug;
    }

    public static void e(String tag,String text){
        if(mIsDebug){
            Log.e(tag,text);
        }
    }

}
