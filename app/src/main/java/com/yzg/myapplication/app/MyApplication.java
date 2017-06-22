package com.yzg.myapplication.app;

import com.yzg.common.app.YApplication;
import com.yzg.common.util.YLog;
import com.yzg.myapplication.BuildConfig;
import com.yzg.myapplication.inject.component.ActivityComponent;
import com.yzg.myapplication.inject.component.AppComponent;
import com.yzg.myapplication.inject.component.DaggerActivityComponent;
import com.yzg.myapplication.inject.component.DaggerAppComponent;
import com.yzg.myapplication.inject.component.DaggerFragmentComponent;
import com.yzg.myapplication.inject.component.FragmentComponent;
import com.yzg.myapplication.inject.module.AppModule;
import com.yzg.myapplication.inject.module.HttpModule;
import com.yzg.myapplication.util.DBUtil;

import java.io.IOException;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by yzg on 2017/3/13.
 */

public class MyApplication extends YApplication {

    private static MyApplication instance;

    public static MyApplication getInstance(){
        return instance;
    }

    private AppComponent appComponent;
    private FragmentComponent fragmentComponent;
    private ActivityComponent activityComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        initComponets();

        Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                try {
                    DBUtil.copyDB2DataBases(MyApplication.this, "area.db");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe();
    }

    @Override
    protected void initTools() {
        super.initTools();
        YLog.setDEBUG(BuildConfig.DEBUG);
    }

    private void initComponets(){
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(instance))
                .httpModule(new HttpModule())
                .build();

        activityComponent = DaggerActivityComponent.builder()
                .appComponent(appComponent)
                .build();

        fragmentComponent = DaggerFragmentComponent.builder()
                .appComponent(appComponent)
                .build();
    }

    public AppComponent getAppComponent(){
        return appComponent;
    }

    public FragmentComponent getFragmentComponent() {
        return fragmentComponent;
    }

    public ActivityComponent getActivityComponent() {
        return activityComponent;
    }
}
