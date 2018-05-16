package com.example.admin.utils.log;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;

import org.apache.http.conn.ssl.SSLSocketFactory;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * @author SZ
 * @fuction
 * @date 2018/1/15
 */

public class OtherUtils {

    private static final int STRING_BUFFER_LENGTH = 100;
    private static TrustManager[] trustAllCerts;

    private OtherUtils() {
    }

    public static String getDiskCacheDir(Context context, String dirName) {
        String cachePath = null;
        if ("mounted".equals(Environment.getExternalStorageState())) {
            File externalCacheDir = context.getExternalCacheDir();
            if (externalCacheDir != null) {
                cachePath = externalCacheDir.getPath();
            }
        }

        if (cachePath == null) {
            cachePath = context.getCacheDir().getPath();
        }

        return cachePath + File.separator + dirName;
    }

    public static long getAvailableSpace(File dir) {
        try {
            StatFs stats = new StatFs(dir.getPath());
            return (long) stats.getBlockSize() * (long) stats.getAvailableBlocks();
        } catch (Throwable var2) {
            LogUtils.e(var2.getMessage(), var2);
            return -1L;
        }
    }

    public static long sizeOfString(String str, String charset) throws UnsupportedEncodingException {
        if (TextUtils.isEmpty(str)) {
            return 0L;
        } else {
            int len = str.length();
            if (len < 100) {
                return (long) str.getBytes(charset).length;
            } else {
                long size = 0L;

                for (int i = 0; i < len; i += 100) {
                    int end = i + 100;
                    end = end < len ? end : len;
                    String temp = getSubString(str, i, end);
                    size += (long) temp.getBytes(charset).length;
                }

                return size;
            }
        }
    }

    public static String getSubString(String str, int start, int end) {
        return new String(str.substring(start, end));
    }

    public static StackTraceElement getCurrentStackTraceElement() {
        return Thread.currentThread().getStackTrace()[3];
    }

    public static StackTraceElement getCallerStackTraceElement() {
        return Thread.currentThread().getStackTrace()[4];
    }

    public static void trustAllSSLForHttpsURLConnection() {
        if (trustAllCerts == null) {
            trustAllCerts = new TrustManager[]{new X509TrustManager() {
                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                @Override
                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }};
        }

        try {
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init((KeyManager[]) null, trustAllCerts, (SecureRandom) null);
            HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
        } catch (Throwable var2) {
            LogUtils.e(var2.getMessage(), var2);
        }

        HttpsURLConnection.setDefaultHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
    }

}
