package com.yzg.myapplication.rx;

import com.yzg.common.base.BaseView;
import com.yzg.common.util.ConnectionUtil;
import com.yzg.myapplication.app.AppException;
import com.yzg.myapplication.model.net.GankResponse;

import rx.Subscriber;

/**
 * Created by yzg on 2017/6/23.
 */

public abstract class GankSubscriber<T> extends Subscriber<GankResponse<T>> {

    public GankSubscriber() {
    }

    public GankSubscriber(Subscriber subscriber) {
        super(subscriber);
    }

    public GankSubscriber(Subscriber subscriber, boolean shareSubscriptions) {
        super(subscriber, shareSubscriptions);
    }

    @Override
    public void onError(Throwable e) {
        if(!ConnectionUtil.isNetworkConnected()){
            _onError(AppException.ConnectionException());
        }else {
            _onError(e);
        }
    }

    @Override
    public void onNext(GankResponse<T> response) {
        if(response.isError()){
            _onError(AppException.ServerException());
        }else {
            _onNext(response);
        }
    }

    @Override
    public void onCompleted() {

    }

    public abstract void _onError(Throwable e);

    public abstract void _onNext(GankResponse<T> response);

}
