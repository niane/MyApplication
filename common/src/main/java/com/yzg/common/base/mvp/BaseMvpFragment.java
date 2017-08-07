package com.yzg.common.base.mvp;

import com.yzg.common.base.BaseFragment;

/**
 * Created by yzg on 2017/7/28.
 */

public abstract class BaseMvpFragment<P extends BasePresenter> extends BaseFragment implements BaseView {
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

    @Override
    protected void onUnBindViewBefore() {
        super.onUnBindViewBefore();
        if(mPresenter != null){
            mPresenter.detachView();
        }
    }
}
