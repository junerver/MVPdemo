package com.junerver.mvpdemo;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.junerver.mvpdemo.contract.ZhihuContract;
import com.junerver.mvpdemo.presenter.ZhihuPresenterImpl;

import org.json.JSONException;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements ZhihuContract.View{
    private ImageView mIvSplash;
    private ZhihuContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mIvSplash = (ImageView) findViewById(R.id.iv_splash);
        mPresenter = new ZhihuPresenterImpl(this);
        try {
            mPresenter.setImage();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void setImage(Bitmap bitmap) {
        mIvSplash.setImageBitmap(bitmap);
    }
}
