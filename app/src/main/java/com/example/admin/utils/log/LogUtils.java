package com.example.admin.utils.log;

import android.text.TextUtils;
import android.util.Log;

/**
 * Log工具，类似android.util.Log。
 * tag自动产生，格式: customTagPrefix:className.methodName(L:lineNumber),
 * customTagPrefix为空时只输出：className.methodName(L:lineNumber)。
 * Author: wyouflf
 * Date: 13-7-24
 * Time: 下午12:23
 * @author SZ
 */
public class LogUtils {

    public static String customTagPrefix = "";
    public static boolean allowD = true;
    public static boolean allowE = true;
    public static boolean allowI = true;
    public static boolean allowV = true;
    public static boolean allowW = true;
    public static boolean allowWtf = true;

    private static boolean mIsDebug = true;

    public static void initLogConfig(boolean isDebug){
        mIsDebug = isDebug;
        new LogUtils();
    }

    private LogUtils() {
        allowD = mIsDebug;
        allowE = mIsDebug;
        allowI = mIsDebug;
        allowV = mIsDebug;
        allowW = mIsDebug;
        allowWtf = mIsDebug;
    }

    private static String generateTag(StackTraceElement caller) {
        String tag = "%s.%s(L:%d)";
        String callerClazzName = caller.getClassName();
        callerClazzName = callerClazzName.substring(callerClazzName.lastIndexOf(".") + 1);
        tag = String.format(tag, new Object[]{callerClazzName, caller.getMethodName(), Integer.valueOf(caller.getLineNumber())});
        tag = TextUtils.isEmpty(customTagPrefix)?tag:customTagPrefix + ":" + tag;
        return tag;
    }

    public static void d(String content) {
        if(allowD) {
            StackTraceElement caller = OtherUtils.getCallerStackTraceElement();
            String tag = generateTag(caller);
                Log.d(tag, content);
        }
    }

    public static void d(String content, Throwable tr) {
        if(allowD) {
            StackTraceElement caller = OtherUtils.getCallerStackTraceElement();
            String tag = generateTag(caller);
                Log.d(tag, content, tr);

        }
    }

    public static void e(String content) {
        if(allowE) {
            StackTraceElement caller = OtherUtils.getCallerStackTraceElement();
            String tag = generateTag(caller);
                Log.e(tag, content);

        }
    }

    public static void e(String content, Throwable tr) {
        if(allowE) {
            StackTraceElement caller = OtherUtils.getCallerStackTraceElement();
            String tag = generateTag(caller);
                Log.e(tag, content, tr);

        }
    }

    public static void i(String content) {
        if(allowI) {
            StackTraceElement caller = OtherUtils.getCallerStackTraceElement();
            String tag = generateTag(caller);
                Log.i(tag, content);

        }
    }

    public static void i(String content, Throwable tr) {
        if(allowI) {
            StackTraceElement caller = OtherUtils.getCallerStackTraceElement();
            String tag = generateTag(caller);
                Log.i(tag, content, tr);

        }
    }

    public static void v(String content) {
        if(allowV) {
            StackTraceElement caller = OtherUtils.getCallerStackTraceElement();
            String tag = generateTag(caller);
                Log.v(tag, content);

        }
    }

    public static void v(String content, Throwable tr) {
        if(allowV) {
            StackTraceElement caller = OtherUtils.getCallerStackTraceElement();
            String tag = generateTag(caller);
                Log.v(tag, content, tr);

        }
    }

    public static void w(String content) {
        if(allowW) {
            StackTraceElement caller = OtherUtils.getCallerStackTraceElement();
            String tag = generateTag(caller);
                Log.w(tag, content);

        }
    }

    public static void w(String content, Throwable tr) {
        if(allowW) {
            StackTraceElement caller = OtherUtils.getCallerStackTraceElement();
            String tag = generateTag(caller);
                Log.w(tag, content, tr);

        }
    }

    public static void w(Throwable tr) {
        if(allowW) {
            StackTraceElement caller = OtherUtils.getCallerStackTraceElement();
            String tag = generateTag(caller);
                Log.w(tag, tr);

        }
    }

    public static void wtf(String content) {
        if(allowWtf) {
            StackTraceElement caller = OtherUtils.getCallerStackTraceElement();
            String tag = generateTag(caller);
                Log.wtf(tag, content);

        }
    }

    public static void wtf(String content, Throwable tr) {
        if(allowWtf) {
            StackTraceElement caller = OtherUtils.getCallerStackTraceElement();
            String tag = generateTag(caller);
                Log.wtf(tag, content, tr);

        }
    }

    public static void wtf(Throwable tr) {
        if(allowWtf) {
            StackTraceElement caller = OtherUtils.getCallerStackTraceElement();
            String tag = generateTag(caller);
                Log.wtf(tag, tr);

        }
    }

}
