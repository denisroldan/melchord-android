package com.aiculabs.melchord.ui.song;

import com.aiculabs.melchord.data.DataManager;
import com.aiculabs.melchord.data.model.Song;
import com.aiculabs.melchord.ui.base.BasePresenter;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class SongPresenter extends BasePresenter<SongMvpView> {
    private final DataManager mDataManager;
    private Subscription mSubscription;

    @Inject
    public SongPresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void attachView(SongMvpView mvpView) {
        super.attachView(mvpView);
    }

    public void getData(String mbid) {
        checkViewAttached();
        mSubscription = mDataManager
                .getSong(mbid)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<Song>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        getMvpView().showError();
                    }

                    @Override
                    public void onNext(Song song) {

                        if (song.getTabSet().isEmpty()) {
                            getMvpView().showNotabs();
                        } else {
                            getMvpView().showSong(song);
                        }
                    }
                });
    }


}
