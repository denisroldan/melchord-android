package com.aiculabs.melchord.ui.search;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
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
import butterknife.OnClick;

public class SearchActivity extends BaseActivity implements SearchMvpView {

    @Inject SearchPresenter mSearchPresenter;
    //@Inject SearchResultAdapter mSearchResultAdapter;

    @Bind(R.id.queryEditText) EditText queryToSearch;
    @Bind(R.id.progressBar) ProgressBar spinner;
    @Bind(R.id.logo) ImageView logoIV;

    @OnClick (R.id.fab)
    void searchBtnPushed(){
        changeUItoLoadingState();
        mSearchPresenter.search(queryToSearch.getText().toString());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivityComponent().inject(this);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        mSearchPresenter.attachView(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onStart() {
        super.onStart();
        changeUItoInitialState();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSearchPresenter.detachView();
    }

    @Override
    public void showResults(List<ArtistSearch> artistSearches) {
        Intent i = new Intent(this, SearchResultsActivity.class);
        startActivity(i);
        //mSearchResultAdapter.setRibots(artistSearches);
        //mSearchResultAdapter.notifyDataSetChanged();
    }

    @Override
    public void showNoResults() {
        // En caso de tener una lista de "cosas" sería necesario aquí vaciar el adapter
        // y decirle al adapter que se actualice
        changeUItoInitialState();
        Toast.makeText(this, R.string.empty_artists_or_songs, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showError() {
        changeUItoInitialState();
        DialogFactory.createGenericErrorDialog(this, getString(R.string.error_loading_artists))
                .show();
    }

    public void changeUItoLoadingState(){
        spinner.setVisibility(View.VISIBLE);
        logoIV.setVisibility(View.GONE);
        queryToSearch.setVisibility(View.GONE);
    }

    public void changeUItoInitialState(){
        spinner.setVisibility(View.GONE);
        logoIV.setVisibility(View.VISIBLE);
        queryToSearch.setVisibility(View.VISIBLE);
    }
}
