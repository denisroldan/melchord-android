package com.aiculabs.melchord.util;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import rx.functions.Func1;
import rx.subjects.PublishSubject;

/**
 * A simple event bus built with RxJava
 */
@Singleton
public class RxEventBus {

    private final PublishSubject<Object> mBusSubject;

    @Inject
    public RxEventBus() {
        mBusSubject = PublishSubject.create();
    }

    /**
     * Posts an object (usually an Event) to the bus
     */
    public void post(Object event) {
        mBusSubject.onNext(event);
    }

    /**
     * Observable that will emmit everything posted to the event bus.
     */
    public Observable<Object> observable() {
        return mBusSubject;
    }

    /**
     * Observable that only emits events of a specific class.
     * Use this if you only want to subscribe to one type of events.
     */
    public <T> Observable<T> filteredObservable(final Class<T> eventClass) {
        return mBusSubject.filter(new Func1<Object, Boolean>() {
            @Override
            public Boolean call(Object event) {
                return eventClass.isInstance(event);
            }
        }).map(new Func1<Object, T>() {
            //Safe to cast because of the previous filter
            @SuppressWarnings("unchecked")
            @Override
            public T call(Object event) {
                return (T) event;
            }
        });
    }
}