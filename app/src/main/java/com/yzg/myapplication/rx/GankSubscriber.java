package com.yzg.myapplication.rx;

import com.yzg.common.app.ExceptionHandler;
import com.yzg.common.app.ServerException;
import com.yzg.common.app.YException;
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
        _onError(ExceptionHandler.handleException(e));
    }

    @Override
    public void onNext(GankResponse<T> response) {
        if(response.isError()){
            _onError(ExceptionHandler.handleException(new ServerException()));
        }else {
            _onNext(response);
        }
    }

    @Override
    public void onCompleted() {

    }

    public abstract void _onError(YException e);

    public abstract void _onNext(GankResponse<T> response);

}
