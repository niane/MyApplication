package com.yzg.myapplication.inject.component;

import android.content.Context;

import com.yzg.myapplication.app.MyApplication;
import com.yzg.myapplication.inject.module.AppModule;
import com.yzg.myapplication.inject.module.HttpModule;
import com.yzg.myapplication.model.net.HttpHelper;
import com.yzg.myapplication.model.orm.AreaDao;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by yzg on 2017/5/2.
 */

@Singleton
@Component(modules = {AppModule.class, HttpModule.class})
public interface AppComponent {
    MyApplication getApplication();

    Context getAppContext();

    AreaDao getAreaDao();

    HttpHelper getHttpHelper();
}
