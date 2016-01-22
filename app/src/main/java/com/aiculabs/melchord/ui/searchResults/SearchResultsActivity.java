package com.aiculabs.melchord.ui.searchResults;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.aiculabs.melchord.R;
import com.aiculabs.melchord.data.model.ArtistSearch;
import com.aiculabs.melchord.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.HashMap;
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

        ArrayList<HashMap<String, String>> results = (ArrayList<HashMap<String, String>>)(getIntent().getSerializableExtra("search_results"));
        this.showResults(results);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSearchResultsPresenter.detachView();
    }

    @Override
    public void showResults(ArrayList<HashMap<String, String>> results) {
        mSearchResultsAdapter.setResults(results);
    }
}
