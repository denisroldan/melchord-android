package com.aiculabs.melchord.ui.searchResults;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.aiculabs.melchord.R;
import com.aiculabs.melchord.data.model.ArtistSearch;
import com.aiculabs.melchord.ui.base.BaseActivity;
import java.util.List;
import javax.inject.Inject;
import butterknife.Bind;
import butterknife.ButterKnife;

public class SearchResultsActivity extends BaseActivity implements SearchResultsMvpView {

    @Inject SearchResultsPresenter mSearchResultsPresenter;
    @Inject SearchResultAdapter mSearchResultsAdapter;

    @Bind(R.id.search_results_recycler_view) RecyclerView searchResultsRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivityComponent().inject(this);
        setContentView(R.layout.activity_search_results);
        ButterKnife.bind(this);
        searchResultsRecyclerView.setAdapter(mSearchResultsAdapter);
        searchResultsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mSearchResultsPresenter.attachView(this);
        mSearchResultsPresenter.showResults();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSearchResultsPresenter.detachView();
    }

    @Override
    public void showResults(List<ArtistSearch> artistSearches) {

    }

    @Override
    public void showNoResults() {
        // TODO: Nunca debería pasar esto si y hacemos el filtro en la activity principal
    }

    @Override
    public void showError() {
        // TODO: Nunca debería pasar esto si y hacemos el filtro en la activity principal
    }
}
