package com.yzg.myapplication.model.net;

import com.yzg.myapplication.model.bean.GankPublishBean;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by yzg on 2017/5/2.
 */

public class RetrofitHelper implements HttpHelper{
    private GankApis gankApis;

    @Inject
    public RetrofitHelper(GankApis gankApis){
        this.gankApis = gankApis;
    }

    @Override
    public Observable<GankResponse<List<GankPublishBean>>> getGankAndroidPublish(int pageSize, int page) {
        return gankApis.getAndroidPublish(pageSize, page);
    }

}
