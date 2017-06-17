package com.yzg.common.base;

/**
 * Created by yzg on 2017/3/10.
 */

public interface BasePresenter<T extends BaseView> {
    void attachView(T view);
    void detachView();
}
