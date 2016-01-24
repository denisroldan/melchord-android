package com.aiculabs.melchord.ui.searchResults;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.aiculabs.melchord.R;
import com.aiculabs.melchord.data.model.ArtistSearch;
import com.aiculabs.melchord.ui.artist.ArtistActivity;
import com.aiculabs.melchord.ui.base.BaseActivity;
import com.aiculabs.melchord.util.CustomItemClickListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.inject.Inject;
import butterknife.Bind;
import butterknife.ButterKnife;

public class SearchResultsActivity extends BaseActivity implements SearchResultsMvpView {

    @Inject SearchResultsPresenter mSearchResultsPresenter;
    private SearchResultAdapter mSearchResultsAdapter;

    @Bind(R.id.search_results_recycler_view) RecyclerView searchResultsRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivityComponent().inject(this);
        setContentView(R.layout.activity_search_results);
        ButterKnife.bind(this);

        final ArrayList<HashMap<String, String>> results = (ArrayList<HashMap<String, String>>)(getIntent().getSerializableExtra("search_results"));

        mSearchResultsAdapter = new SearchResultAdapter(new CustomItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Intent i = new Intent(getBaseContext(), ArtistActivity.class);
                i.putExtra("mbid", results.get(position).get("mbid"));
                startActivity(i);
            }
        });
        searchResultsRecyclerView.setAdapter(mSearchResultsAdapter);
        searchResultsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

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
