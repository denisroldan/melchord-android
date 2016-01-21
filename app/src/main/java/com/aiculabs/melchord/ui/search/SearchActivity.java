package com.aiculabs.melchord.ui.search;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.aiculabs.melchord.R;
import com.aiculabs.melchord.data.model.ArtistSearch;
import com.aiculabs.melchord.ui.artist.ArtistActivity;
import com.aiculabs.melchord.ui.base.BaseActivity;
import com.aiculabs.melchord.ui.searchResults.SearchResultsActivity;
import com.aiculabs.melchord.ui.searchResults.SearchResultsPresenter;
import com.aiculabs.melchord.util.DialogFactory;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SearchActivity extends BaseActivity implements SearchMvpView {

    @Inject SearchPresenter mSearchPresenter;
    //@Inject SearchResultAdapter mSearchResultAdapter;

    @Bind(R.id.queryEditText) EditText queryToSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivityComponent().inject(this);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        mSearchPresenter.attachView(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSearchPresenter.search(queryToSearch.getText().toString());
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSearchPresenter.detachView();
    }

    @Override
    public void showResults(List<ArtistSearch> artistSearches) {
        //TODO - Esto seguramente vaya en la vista de Resultados... :/
        Intent i = new Intent(this, SearchResultsActivity.class);
        startActivity(i);

        //mSearchResultAdapter.setRibots(artistSearches);
        //mSearchResultAdapter.notifyDataSetChanged();
    }

    @Override
    public void showNoResults() {
        // En caso de tener una lista de "cosas" sería necesario aquí vaciar el adapter
        // y decirle al adapter que se actualice
        Toast.makeText(this, R.string.empty_artists_or_songs, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showError() {
        DialogFactory.createGenericErrorDialog(this, getString(R.string.error_loading_artists))
                .show();
    }
}
