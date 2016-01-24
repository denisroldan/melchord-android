package com.aiculabs.melchord.ui.tab;

import com.aiculabs.melchord.data.DataManager;
import com.aiculabs.melchord.data.model.Song;
import com.aiculabs.melchord.data.model.Tab;
import com.aiculabs.melchord.ui.base.BasePresenter;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class TabPresenter extends BasePresenter<TabMvpView> {
    private final DataManager mDataManager;
    private Subscription mSubscription;

    @Inject
    public TabPresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void attachView(TabMvpView mvpView) {
        super.attachView(mvpView);
    }

    public void getData(Integer id) {
        checkViewAttached();
        mSubscription = mDataManager
                .getTab(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<Tab>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        getMvpView().showError();
                    }

                    @Override
                    public void onNext(Tab tab) {
                        getMvpView().showTab(tab);
                    }
                });
    }


}
