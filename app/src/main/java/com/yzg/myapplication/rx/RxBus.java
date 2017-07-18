package com.yzg.myapplication.rx;

import android.support.annotation.NonNull;

import rx.Observable;
import rx.Scheduler;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * Created by yzg on 2017/7/18.
 */

public class RxBus {
    private static volatile RxBus instance;

    private final Subject<Object, Object> bus;

    private RxManager rxManager;

    private RxBus() {
        bus = new SerializedSubject<>(PublishSubject.create());
        rxManager = RxManager.getInstance();
    }

    public static RxBus getInstance() {
        if (instance == null) {
            synchronized (RxBus.class) {
                if (instance == null) {
                    instance = new RxBus();
                }
            }
        }
        return instance ;
    }

    public void post (Object o) {
        bus.onNext(o);
    }

    /**
     * 接收事件
     *
     * @param eventType 事件类型
     * @param scheduler 事件处理调度器
     * @param holder 事件监听持有者
     * @param action 事件处理
     * @param <T>
     * @return
     */
    public<T> Subscription onEvent(Class<T> eventType, Scheduler scheduler, @NonNull Object holder, Action1<T> action){
        Subscription subscription = toObservable(eventType)
                .observeOn(scheduler)
                .subscribe(action);
        rxManager.add(holder, subscription);
        return  subscription;
    }

    /**
     * 接收事件（默认在主线程处理事件）
     *
     * @param eventType 事件类型
     * @param holder 事件监听持有者
     * @param action 事件处理
     * @param <T>
     * @return
     */
    public<T> Subscription onEvent(Class<T> eventType, Object holder, Action1<T> action){
        return onEvent(eventType, AndroidSchedulers.mainThread(), holder, action);
    }

    /**
     * 取消监听
     * @param holder 事件监听持有者
     */
    public void unsubscribe(Object holder){
        if(holder == null) return;
        rxManager.remove(holder);
    }

    public <T> Observable<T> toObservable (Class<T> eventType) {
        return bus.ofType(eventType);
    }
}
