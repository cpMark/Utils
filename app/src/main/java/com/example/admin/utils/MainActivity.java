package com.example.admin.utils;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

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
