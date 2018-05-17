package com.example.admin.utils.network.builder;

import com.example.admin.utils.network.NetworkManager;
import com.example.admin.utils.network.request.OtherRequest;
import com.example.admin.utils.network.request.RequestCall;

/**
 * function：Head请求构建者
 * author by admin
 * create on 2018/5/17.
 */
public class HeadBuilder extends GetBuilder{

    @Override
    public RequestCall build() {
        return new OtherRequest(null, null, NetworkManager.METHOD.HEAD, mUrl, mTag, mParams, mHeaders,mId).build();
    }
}
