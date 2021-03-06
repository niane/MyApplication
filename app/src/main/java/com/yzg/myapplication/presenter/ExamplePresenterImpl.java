package com.yzg.myapplication.presenter;


import android.content.Context;

import com.yzg.common.app.YException;
import com.yzg.myapplication.R;
import com.yzg.myapplication.app.MyApplication;
import com.yzg.myapplication.presenter.contract.ExampleContract;
import com.yzg.myapplication.model.bean.GankPublishBean;
import com.yzg.myapplication.model.bean.GankResponse;
import com.yzg.myapplication.model.net.HttpHelper;
import com.yzg.myapplication.rx.GankSubscriber;
import com.yzg.common.rx.RxPresenter;
import com.yzg.simplerecyclerview.adapter.RecyViewHolder;
import com.yzg.simplerecyclerview.adapter.SimpleRecyAdapter;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by yzg on 2017/3/10.
 */

public class ExamplePresenterImpl extends RxPresenter<ExampleContract.ExampleView> implements ExampleContract.ExamplePresenter {
    private ExampleContract.ExampleView mView;
    private HttpHelper httpHelper;

    public ExamplePresenterImpl() {
        this.httpHelper = MyApplication.getInstance().getAppComponent().getHttpHelper();
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
    public SimpleRecyAdapter<GankPublishBean> createAdapter(Context context, List<GankPublishBean> list) {
        return new SimpleRecyAdapter<GankPublishBean>(context, R.layout.listview_item, list){
            @Override
            protected void convert(RecyViewHolder viewHolder, GankPublishBean gankPublishBean, int position) {
                viewHolder.setText(R.id.text, gankPublishBean.getDesc());
            }
        };
    }

    @Override
    public void getGankPublish(int pageSize, final int page) {
        add(httpHelper.getGankAndroidPublish(pageSize, page)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new GankSubscriber<List<GankPublishBean>>() {
                    @Override
                    public void _onError(YException e) {
                        mView.onLoadError(e, page);
                        mView.showMessage(e.getMessage());
                    }

                    @Override
                    public void _onNext(GankResponse<List<GankPublishBean>> response) {
                        mView.onReturnList(response.getResults(), page);
                    }
                }));
    }
}
