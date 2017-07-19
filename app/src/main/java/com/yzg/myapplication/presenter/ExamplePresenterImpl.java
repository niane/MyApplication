package com.yzg.myapplication.presenter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.yzg.common.base.BaseView;
import com.yzg.myapplication.R;
import com.yzg.myapplication.presenter.contract.ExampleContract;
import com.yzg.myapplication.model.bean.GankPublishBean;
import com.yzg.myapplication.model.net.GankResponse;
import com.yzg.myapplication.model.net.HttpHelper;
import com.yzg.myapplication.rx.GankSubscriber;
import com.yzg.myapplication.rx.RxPresenter;
import com.yzg.simplerecyclerview.adapter.RecyViewHolder;
import com.yzg.simplerecyclerview.adapter.SimpleRecyAdapter;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by yzg on 2017/3/10.
 */

public class ExamplePresenterImpl extends RxPresenter<ExampleContract.ExampleView> implements ExampleContract.ExamplePresenter {
    private ExampleContract.ExampleView mView;
    private HttpHelper httpHelper;

    @Inject
    public ExamplePresenterImpl(HttpHelper httpHelper) {
        this.httpHelper = httpHelper;
    }

    @Override
    public void attachView(ExampleContract.ExampleView view) {
        super.attachView(view);
        mView = view;
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    @Override
    public RecyclerView.Adapter createAdapter(Context context, List<GankPublishBean> list) {
        return new SimpleRecyAdapter<GankPublishBean>(context, R.layout.listview_item, list){
            @Override
            protected void convert(RecyViewHolder viewHolder, GankPublishBean gankPublishBean, int position) {
                viewHolder.setText(R.id.text, gankPublishBean.getDesc());
            }
        };
    }

    @Override
    public void getGankPublish(int pageSize, int page) {
        add(httpHelper.getGankAndroidPublish(pageSize, page)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new GankSubscriber<List<GankPublishBean>>() {
                    @Override
                    public void _onError(Throwable e) {
                        mView.onLoadError();
                        mView.showMessage(e.getMessage());
                    }

                    @Override
                    public void _onNext(GankResponse<List<GankPublishBean>> response) {
                        mView.onReturnList(response.getResults());
                    }
                }));
    }
}
