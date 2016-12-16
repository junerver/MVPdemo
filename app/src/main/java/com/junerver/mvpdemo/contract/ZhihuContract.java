package com.junerver.mvpdemo.contract;

import android.graphics.Bitmap;

import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;

import java.io.IOException;

/**
 * Created by Junerver on 2016/12/16.
 * Feature:
 * Updated:
 */
public class ZhihuContract {
public interface View{
    void setImage(Bitmap bitmap);
}

public interface Presenter{
    void setImage() throws IOException, JSONException;
}

public interface Model{
    void getImage(StringCallback callback) throws IOException, JSONException;
}


}