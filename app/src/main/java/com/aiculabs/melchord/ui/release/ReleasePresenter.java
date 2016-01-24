package com.aiculabs.melchord.ui.release;

import com.aiculabs.melchord.data.DataManager;
import com.aiculabs.melchord.data.model.Release;
import com.aiculabs.melchord.ui.base.BasePresenter;


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
                .subscribe(new Subscriber<Release>() {
                    @Override
                    public void onCompleted() {
                        //TODO - Se supone que aquí tendremos más cosillas...
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e(e, "There was an error getting the release...");
                        getMvpView().showError();
                    }

                    @Override
                    public void onNext(Release release) {
                        if (release != null){
                            getMvpView().showRelease(release);
                        }else {
                            getMvpView().showNoResults();
                        }

                    }
                });
    }

}
