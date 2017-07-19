package com.yzg.myapplication.rx;

import android.support.annotation.NonNull;

import java.util.concurrent.ConcurrentHashMap;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * 统一管理订阅包括RxBus<br/>
 * 以订阅持有者为单元，相同持有者的所有订阅放在一个CompositeSubscription<br/>
 * Created by yzg on 2017/7/18.
 */

public class RxManager {

    private static volatile RxManager instance;
    /**
     * 用于管理订阅<br/>
     * 以订阅持有者为单元，相同持有者的所有订阅放在一个CompositeSubscription
     */
    private final ConcurrentHashMap<Object, CompositeSubscription> compositeSubscriptions;

    private RxManager(){
        compositeSubscriptions = new ConcurrentHashMap<>();
    }

    public static RxManager getInstance() {
        if (instance == null) {
            synchronized (RxManager.class) {
                if (instance == null) {
                    instance = new RxManager();
                }
            }
        }
        return instance ;
    }

    /**
     * 添加订阅
     * @param holder 订阅持有者
     * @param subscription
     */
    public void add(@NonNull Object holder, Subscription subscription){
        CompositeSubscription compositeSubscription = compositeSubscriptions.get(holder);
        if(compositeSubscription == null){
            compositeSubscription = new CompositeSubscription();
            compositeSubscriptions.put(holder, compositeSubscription);
        }
        compositeSubscription.add(subscription);
    }

    /**
     * 取消订阅
     * @param holder 订阅持有者
     */
    public void remove(Object holder){
        if(holder == null) return;
        CompositeSubscription compositeSubscription = compositeSubscriptions.get(holder);
        if(compositeSubscription != null){
            compositeSubscription.unsubscribe();
            compositeSubscriptions.remove(compositeSubscription);
        }
    }
}
