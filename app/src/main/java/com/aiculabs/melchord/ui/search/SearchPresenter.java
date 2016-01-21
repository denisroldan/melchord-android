package com.aiculabs.melchord.ui.search;

import android.util.Log;

import com.aiculabs.melchord.data.DataManager;
import com.aiculabs.melchord.data.model.ArtistSearch;
import com.aiculabs.melchord.ui.base.BasePresenter;
import com.aiculabs.melchord.ui.main.MainMvpView;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

public class SearchPresenter extends BasePresenter<SearchMvpView> {
    private final DataManager mDataManager;
    private Subscription mSubscription;

    @Inject
    public SearchPresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void attachView(SearchMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
        if (mSubscription != null) mSubscription.unsubscribe();
    }

    public void search(String query) {
        checkViewAttached();

        mSubscription = mDataManager.getSearchResults(query)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<List<ArtistSearch>>() {
                    @Override
                    public void onCompleted() {
                        Log.wtf("", "HOLA");
                        //TODO - Se supone que auí tendremos más cosillas...
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e(e, "There was an error searching...");
                        getMvpView().showError();
                    }

                    @Override
                    public void onNext(List<ArtistSearch> artistSearches) {
                        if (artistSearches.isEmpty()) {
                            getMvpView().showNoResults();
                        } else {
                            getMvpView().showResults(artistSearches);
                        }
                    }
                });
    }

}
