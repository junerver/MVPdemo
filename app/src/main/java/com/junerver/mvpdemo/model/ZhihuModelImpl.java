package com.junerver.mvpdemo.model;

import com.junerver.mvpdemo.contract.ZhihuContract;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;

import java.io.IOException;

/**
* Created by Mike on 2016/12/16
*/

public class ZhihuModelImpl implements ZhihuContract.Model{

    @Override
    public void getImage(StringCallback callback) throws IOException, JSONException {

        OkHttpUtils
                .get()
                .url("http://news-at.zhihu.com/api/4/start-image/1080*1776")
                .build()
                .execute(callback);

    }

}