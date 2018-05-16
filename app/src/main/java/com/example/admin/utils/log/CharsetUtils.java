package com.example.admin.utils.log;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author SZ
 * @fuction
 * @date 2018/1/15
 */

public class CharsetUtils {
    public static final String DEFAULT_ENCODING_CHARSET = "ISO-8859-1";
    public static final List<String> SUPPORT_CHARSET = new ArrayList();

    static {
        SUPPORT_CHARSET.add("ISO-8859-1");
        SUPPORT_CHARSET.add("GB2312");
        SUPPORT_CHARSET.add("GBK");
        SUPPORT_CHARSET.add("GB18030");
        SUPPORT_CHARSET.add("US-ASCII");
        SUPPORT_CHARSET.add("ASCII");
        SUPPORT_CHARSET.add("ISO-2022-KR");
        SUPPORT_CHARSET.add("ISO-8859-2");
        SUPPORT_CHARSET.add("ISO-2022-JP");
        SUPPORT_CHARSET.add("ISO-2022-JP-2");
        SUPPORT_CHARSET.add("UTF-8");
    }

    private CharsetUtils() {
    }

    public static String toCharset(String str, String charset, int judgeCharsetLength) {
        try {
            String oldCharset = getEncoding(str, judgeCharsetLength);
            return new String(str.getBytes(oldCharset), charset);
        } catch (Throwable var4) {
            LogUtils.w(var4);
            return str;
        }
    }

    public static String getEncoding(String str, int judgeCharsetLength) {
        String encode = "ISO-8859-1";
        Iterator var4 = SUPPORT_CHARSET.iterator();

        while(var4.hasNext()) {
            String charset = (String)var4.next();
            if(isCharset(str, charset, judgeCharsetLength)) {
                encode = charset;
                break;
            }
        }

        return encode;
    }

    public static boolean isCharset(String str, String charset, int judgeCharsetLength) {
        try {
            String temp = str.length() > judgeCharsetLength?str.substring(0, judgeCharsetLength):str;
            return temp.equals(new String(temp.getBytes(charset), charset));
        } catch (Throwable var4) {
            return false;
        }
    }
}
