package com.example.admin.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.admin.utils.network.NetworkManager;
import com.squareup.picasso.Picasso;

import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.btn_download_picture)
    Button mBtnDownloadPicture;
    @BindView(R.id.iv_show_download)
    ImageView mIvShowDownload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initListener();
    }

    private void initListener() {
        mBtnDownloadPicture.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Observable<Response> observable = NetworkManager.getInstance().getRequest("http://www.wanandroid.com/resources/image/pc/logo.png");
        observable
                .map(new Function<Response, Bitmap>() {
            @Override
            public Bitmap apply(Response response) throws Exception {
                return doParse(response.body().);
            }
        }).observeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Bitmap>() {
                    @Override
                    public void accept(Bitmap bitmap) throws Exception {
                        mIvShowDownload.setImageBitmap(bitmap);
                    }
                });
    }

    private Bitmap doParse(byte[] response) {
        BitmapFactory.Options decodeOptions = new BitmapFactory.Options();
        Bitmap bitmap = null;
        decodeOptions.inPreferredConfig = Bitmap.Config.RGB_565;
        return BitmapFactory.decodeByteArray(response, 0, response.length, decodeOptions);
    }
}
