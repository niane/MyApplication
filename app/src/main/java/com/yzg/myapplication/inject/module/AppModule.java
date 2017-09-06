package com.yzg.myapplication.inject.module;

import android.content.Context;

import com.yzg.myapplication.app.MyApplication;
import com.yzg.myapplication.model.net.HttpHelper;
import com.yzg.myapplication.model.net.RetrofitHelper;
import com.yzg.myapplication.model.orm.AreaDao;
import com.yzg.myapplication.model.orm.greendao.GAreaDao;
import com.yzg.myapplication.model.orm.greendao.GDataBaseHelper;
import com.yzg.myapplication.model.orm.ormlite.LAreaDao;
import com.yzg.myapplication.model.orm.ormlite.LDataBaseHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by yzg on 2017/5/2.
 */

@Module
public class AppModule {
    private final MyApplication myApplication;

    public AppModule(MyApplication myApplication){
        this.myApplication = myApplication;
    }

    @Provides
    @Singleton
    MyApplication provideApplication(){
        return myApplication;
    }

    @Provides
    @Singleton
    Context provideAppContext(){
        return myApplication;
    }

//    @Provides
//    @Singleton
//    AreaDao provideAreaDao(LDataBaseHelper helper){
//        return new LAreaDao(helper);
//    }

    @Provides
    @Singleton
    AreaDao provideAreaDao(GDataBaseHelper helper){
        return new GAreaDao(helper);
    }

    @Provides
    @Singleton
    HttpHelper provideHttpHelper(RetrofitHelper retrofitHelper){
        return retrofitHelper;
    }
}
