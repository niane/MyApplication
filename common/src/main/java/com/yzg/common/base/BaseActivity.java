package com.yzg.common.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Created by yzg on 2017/3/10.
 */

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements BaseView{
    @Inject
    protected P mPresenter;

    protected abstract @LayoutRes int getContentLayoutRes();
    protected abstract void initViews(View rootView);
    protected abstract void initInject();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView = LayoutInflater.from(this).inflate(getContentLayoutRes(), null);
        setContentView(rootView);

        ButterKnife.bind(this);

        initInject();
        if(mPresenter != null){
            mPresenter.attachView(this);
        }
        initViews(rootView);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mPresenter != null){
            mPresenter.detachView();
        }
        ButterKnife.unbind(this);
    }
}
