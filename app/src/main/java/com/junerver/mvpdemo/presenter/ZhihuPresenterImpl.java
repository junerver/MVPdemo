package com.junerver.mvpdemo.presenter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

import com.junerver.mvpdemo.contract.ZhihuContract;
import com.junerver.mvpdemo.model.ZhihuModelImpl;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;

/**
* Created by Mike on 2016/12/16
*/

public class ZhihuPresenterImpl implements ZhihuContract.Presenter{
    ZhihuContract.View mView;
    ZhihuContract.Model mModel;

    public ZhihuPresenterImpl(ZhihuContract.View view) {
        mView = view;
        mModel = new ZhihuModelImpl();
    }

    @Override
    public void setImage() throws IOException, JSONException {
        mModel.getImage(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String imageUrl = jsonObject.optString("img");
                    String fileName = imageUrl.substring(imageUrl.lastIndexOf("/"));
                    File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator + fileName);
                    if (file.isFile() && file.exists()) {
                        Log.e("filename:", file.getAbsolutePath() + "       is exists");
                        Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                        mView.setImage(bitmap);
                    } else {
                        OkHttpUtils//
                                .get()//
                                .url(imageUrl)//
                                .build()//
                                .execute(new FileCallBack(Environment.getExternalStorageDirectory().getAbsolutePath(), fileName)//
                                {
                                    @Override
                                    public void onError(Call call, Exception e, int id) {

                                    }

                                    @Override
                                    public void onResponse(File response, int id) {
                                        Log.e("filename:", response.getAbsolutePath());
                                        Bitmap bitmap = BitmapFactory.decodeFile(response.getAbsolutePath());
                                        mView.setImage(bitmap);
                                    }

                                });
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}