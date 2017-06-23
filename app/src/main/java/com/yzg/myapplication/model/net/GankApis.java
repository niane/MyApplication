package com.yzg.myapplication.model.net;

import com.yzg.myapplication.model.bean.GankPublishBean;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by yzg on 2017/5/2.
 */

public interface GankApis {
    String BASE_URL = "http://gank.io/api/";

    @GET("data/Android/{pageSize}/{page}")
    Observable<GankResponse<List<GankPublishBean>>> getAndroidPublish(@Path("pageSize") int pageSize, @Path("page") int page);
}
