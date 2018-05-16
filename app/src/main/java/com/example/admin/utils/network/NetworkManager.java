package com.example.admin.utils.network;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * function：外观模式，将各个零件在这里整合，方便使用
 * author by cpMark
 * create on 2018/5/15.
 */
public class NetworkManager {

    private volatile static NetworkManager sInstance;

    private NetworkManager(){
    }

    public static NetworkManager getInstance(){
        if(sInstance == null){
            synchronized (OkhttpClientInstance.class){
                if(sInstance == null){
                    sInstance = new NetworkManager();
                }
            }
        }

        return sInstance;
    }

    public Observable<Response> getRequest(String url){
        final Request request = new Request.Builder().url(url).build();
        return Observable.create(new ObservableOnSubscribe<Response>() {
            @Override
            public void subscribe(ObservableEmitter<Response> emitter) throws Exception {
                Response response = OkhttpClientInstance.getInstance().getClient().newCall(request).execute();
                emitter.onNext(response);
            }
        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());

    }

}
