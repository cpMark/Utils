package com.example.admin.utils;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.admin.utils.aes.AESUtils;
import com.example.admin.utils.log.LogUtils;
import com.example.admin.utils.network.NetworkManager;
import com.example.admin.utils.network.callback.StringCallBack;
import com.google.gson.Gson;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        NetworkManager
//                .get()
//                .url("http://cdn.poker.top/bat.txt?")
//                .addParams("v", System.currentTimeMillis() + "")
//                .build()
//                .execute(new StringCallBack() {
//                    @Override
//                    public void onError(Call call, Exception e, int id) {
//                        Toast.makeText(MainActivity.this, "onError", Toast.LENGTH_SHORT).show();
//                    }
//
//                    @SuppressLint("CheckResult")
//                    @Override
//                    public void onResponse(final String response, int id) {
//                        Observable.create(new ObservableOnSubscribe<String>() {
//                            @Override
//                            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
//                                String domainsString = AESUtils.aes128CbcDecrypt("lZ2hFQWobg7uNHXG", response, "0f9387b3f94bf06f");
//                                emitter.onNext(domainsString);
//                            }
//                        }).map(new Function<String, TestBean[]>() {
//                            @Override
//                            public TestBean[] apply(String s) throws Exception {
//                                return new Gson().fromJson(s, TestBean[].class);
//                            }
//                        })
//                                .subscribeOn(Schedulers.io())
//                                .observeOn(AndroidSchedulers.mainThread())
//                                .subscribe(new Consumer<TestBean[]>() {
//                                    @Override
//                                    public void accept(TestBean[] testBeans) throws Exception {
//                                        if (testBeans != null) {
//                                            for (TestBean testBean : testBeans) {
//                                                LogUtils.e(TAG, testBean.toString());
//                                            }
//                                        } else {
//                                            Toast.makeText(MainActivity.this, "Result is empty!", Toast.LENGTH_SHORT).show();
//                                        }
//                                    }
//                                });
//                    }
//                });
    }

}
