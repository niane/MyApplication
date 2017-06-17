package com.yzg.myapplication.model.network;

import com.yzg.myapplication.model.bean.GankPublishBean;

import java.util.List;

import rx.Observable;

/**
 * Created by yzg on 2017/5/3.
 */

public interface HttpHelper {
    Observable<GankResponse<List<GankPublishBean>>> getGankAndroidPublish(int pageSize, int page);
}
