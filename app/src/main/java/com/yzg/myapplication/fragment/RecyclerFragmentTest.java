package com.yzg.myapplication.fragment;

import com.yzg.common.base.mvp.BaseRecyclerFragment;
import com.yzg.myapplication.app.MyApplication;
import com.yzg.myapplication.presenter.FragmentPresenterImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by yzg on 2017/3/31.
 */

public class RecyclerFragmentTest extends BaseRecyclerFragment<String, FragmentPresenterImpl> {
    private Subscription subscription;

    @Override
    protected void requestData(final int pageNO, final int pageSize) {
        subscription = Observable.create(new Observable.OnSubscribe<List<String>>() {
            @Override
            public void call(Subscriber<? super List<String>> subscriber) {
                List<String> list = new ArrayList<>();
                for(int i = 0; i < pageSize; i++){
                    list.add("Item " + i);
                }
                subscriber.onNext(list);
            }
        }).delay(3, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Action1<List<String>>() {
                    @Override
                    public void call(List<String> strings) {
                        onReturnList(strings, pageNO);
                    }
                });
    }

    @Override
    protected boolean needRefreshOnCreate() {
        return false;
    }

    @Override
    public FragmentPresenterImpl newPresenter() {
        return new FragmentPresenterImpl();
    }
}
