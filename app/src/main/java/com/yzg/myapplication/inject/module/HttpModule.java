package com.yzg.myapplication.inject.module;

import com.yzg.myapplication.BuildConfig;
import com.yzg.myapplication.model.network.GankApis;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by yzg on 2017/5/2.
 */

@Module
public class HttpModule {

    @Provides
    @Singleton
    GankApis provideGankApis(OkHttpClient client){
        return createRetrofit(GankApis.BASE_URL, client).create(GankApis.class);
    }

    @Provides
    @Singleton
    OkHttpClient provideHttpClient(){
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();

        if(BuildConfig.DEBUG){
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            clientBuilder.addInterceptor(loggingInterceptor);
        }

        return clientBuilder.build();
    }

    private Retrofit createRetrofit(String url, OkHttpClient client){
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder();
        retrofitBuilder.baseUrl(url)
                .client(client)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create());

        return retrofitBuilder.build();
    }
}
