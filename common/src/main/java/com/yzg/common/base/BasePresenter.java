package com.yzg.common.base;

/**
 * Created by yzg on 2017/3/10.
 */

public interface BasePresenter<V extends BaseView> {
    void attachView(V view);
    void detachView();
}
