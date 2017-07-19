package com.yzg.myapplication.rx;

import com.yzg.common.base.BasePresenter;
import com.yzg.common.base.BaseView;
import com.yzg.common.util.YLog;

import rx.Subscription;

/**
 * 管理BaseView持有的订阅<br/>
 * 调用detachView()时会取消BaseView所持有的所有订阅<br/>
 * Created by yzg on 2017/7/19.
 */

public abstract class RxPresenter<V extends BaseView> implements BasePresenter<V> {
    private final String TAG = "RxPresenter";
    private BaseView holder;

    /**
     * 添加订阅
     * @param subscription
     */
    public void add(Subscription subscription){
        RxManager.getInstance().add(holder, subscription);
    }

    @Override
    public void attachView(V view) {
        holder = view;
    }

    /**
     * 取消订阅
     */
    @Override
    public void detachView() {
        YLog.d(TAG, "Remove subscription, the holder is -------" + holder.getClass().getSimpleName());
        RxManager.getInstance().remove(holder);
    }
}
