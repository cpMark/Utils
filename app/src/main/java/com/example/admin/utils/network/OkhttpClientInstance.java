package com.example.admin.utils.network;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * function：
 * author by cpMark
 * create on 2018/5/15.
 */
public class OkhttpClientInstance {

    private volatile static OkhttpClientInstance sInstance;
    private OkHttpClient mClient;

    private OkhttpClientInstance(){
        mClient = new OkHttpClient();
        mClient.newBuilder().readTimeout(NetwordDefaultParams.DEFAULT_SECOND_OF_READ_TIMEOUT, TimeUnit.SECONDS).build();
    }

    public static OkhttpClientInstance getInstance(){
        if(sInstance == null){
            synchronized (OkhttpClientInstance.class){
                if(sInstance == null){
                    sInstance = new OkhttpClientInstance();
                }
            }
        }

        return sInstance;
    }

    public OkHttpClient getClient() {
        return mClient;
    }
}
