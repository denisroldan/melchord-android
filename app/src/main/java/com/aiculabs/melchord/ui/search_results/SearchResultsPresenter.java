package com.aiculabs.melchord.ui.search_results;

import com.aiculabs.melchord.data.DataManager;
import com.aiculabs.melchord.ui.base.BasePresenter;

import javax.inject.Inject;

import rx.Subscription;

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

}
