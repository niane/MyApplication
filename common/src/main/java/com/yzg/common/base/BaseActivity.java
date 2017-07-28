package com.yzg.common.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by yzg on 2017/3/10.
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected abstract @LayoutRes int getContentLayoutRes();
    protected abstract void initViews(View rootView);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView = LayoutInflater.from(this).inflate(getContentLayoutRes(), null);
        setContentView(rootView);

        initIntent(getIntent());
        onRestoreInstanceState(savedInstanceState);
        initWindow();
        onBindViewBefore();

        ButterKnife.bind(this);

        initViews(rootView);
        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        onUnBindViewBefore();
        ButterKnife.unbind(this);
    }

    /**
     * 初始化参数
     *
     * @param intent
     */
    protected void initIntent(Intent intent) {

    }

    protected void initWindow() {
    }

    protected void initData() {
    }

    protected void onBindViewBefore() {
    }

    protected void onUnBindViewBefore(){
    }

    protected void onRestoreInstanceState(Bundle bundle) {
    }
}
