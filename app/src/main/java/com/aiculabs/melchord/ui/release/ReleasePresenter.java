package com.aiculabs.melchord.ui.release;

import android.util.Log;

import com.aiculabs.melchord.data.DataManager;
import com.aiculabs.melchord.data.model.ArtistSearch;
import com.aiculabs.melchord.data.model.Release;
import com.aiculabs.melchord.ui.base.BasePresenter;
import com.aiculabs.melchord.ui.main.MainMvpView;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

public class ReleasePresenter extends BasePresenter<ReleaseMvpView> {
    private final DataManager mDataManager;
    private Subscription mSubscription;

    @Inject
    public ReleasePresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void attachView(ReleaseMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
        if (mSubscription != null) mSubscription.unsubscribe();
    }

    public void getRelease(String mbid) {
        checkViewAttached();

        mSubscription = mDataManager.getReleaseResults(mbid)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<List<Release>>() {
                    @Override
                    public void onCompleted() {
                        //TODO - Se supone que auí tendremos más cosillas...
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e(e, "There was an error searching...");
                        getMvpView().showError();
                    }

                    @Override
                    public void onNext(List<Release> releaseList) {
                        if (releaseList.isEmpty()) {
                            getMvpView().showNoResults();
                        } else {
                            getMvpView().showResults(releaseList);
                        }
                    }
                });
    }

}
