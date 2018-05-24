package com.example.admin.utils.string;

import java.util.Locale;

/**
 * function：字符串格式化工具类
 * author by admin
 * create on 2018/5/24.
 */
public class FormatUtils {

    /**
     * 占位符字符串格式化
     *
     * @param format 待格式化的字符串
     * @param args   可变字符串（用于格式化字符串的占位符）
     */
    public static String format(String format, Object... args) {
        return String.format(Locale.US, format, args);
    }
}
