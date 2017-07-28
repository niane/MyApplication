package com.yzg.common.base.mvp;

import com.yzg.common.base.BaseActivity;

/**
 * Created by yzg on 2017/7/28.
 */

public abstract class BaseMvpActivity<P extends BasePresenter> extends BaseActivity implements BaseView {
    protected P mPresenter;

    public abstract P newPresenter();

    @Override
    protected void onBindViewBefore() {
        super.onBindViewBefore();
        mPresenter = newPresenter();
        if(mPresenter != null){
            mPresenter.attachView(this);
        }
    }
}
