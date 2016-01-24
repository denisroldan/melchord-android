package com.aiculabs.melchord.ui.artist;

import android.support.design.widget.Snackbar;
import android.util.Log;

import com.aiculabs.melchord.data.DataManager;
import com.aiculabs.melchord.data.model.Artist;
import com.aiculabs.melchord.ui.base.BasePresenter;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ArtistPresenter extends BasePresenter<ArtistMvpView> {
    private final DataManager mDataManager;
    private Subscription mSubscription;

    @Inject
    public ArtistPresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void attachView(ArtistMvpView mvpView) {
        super.attachView(mvpView);
    }

    public void getData(String mbid) {
        checkViewAttached();
        mSubscription = mDataManager
                .getArtist(mbid)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<Artist>() {
                    @Override
                    public void onCompleted() {}

                    @Override
                    public void onError(Throwable e) {getMvpView().showError();}

                    @Override
                    public void onNext(Artist artist) {
                        if (artist.getReleaseSet().isEmpty()) {
                            getMvpView().emptyArtist();
                        } else {
                            getMvpView().showArtist(artist);
                        }
                    }
                });
    }
}
