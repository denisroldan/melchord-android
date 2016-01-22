package com.aiculabs.melchord.ui.searchResults;

import com.aiculabs.melchord.data.DataManager;
import com.aiculabs.melchord.data.model.ArtistSearch;
import com.aiculabs.melchord.ui.base.BasePresenter;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

public class SearchResultsPresenter extends BasePresenter<SearchResultsMvpView> {
    private final DataManager mDataManager;
    private Subscription mSubscription;

    @Inject
    public SearchResultsPresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void attachView(SearchResultsMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
        if (mSubscription != null) mSubscription.unsubscribe();
    }

    public void showResults() {
        checkViewAttached();

        mSubscription = mDataManager.getSearchResults("muse")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<List<ArtistSearch>>() {
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
