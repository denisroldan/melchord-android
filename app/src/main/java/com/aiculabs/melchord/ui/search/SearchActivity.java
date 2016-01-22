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
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends BaseActivity implements SearchMvpView {

    @Inject SearchPresenter mSearchPresenter;

    @Bind(R.id.queryEditText) EditText queryToSearch;
    @Bind(R.id.progressBar) ProgressBar spinner;
    @Bind(R.id.logo) ImageView logoIV;

    @OnClick (R.id.fab)
    void searchBtnPushed(){
        animateUItoLoadingState();
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
        animateUItoInitialState();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSearchPresenter.detachView();
    }

    @Override
    public void showResults(List<ArtistSearch> artistSearches) {
        Intent i = new Intent(this, ArtistActivity.class);
        i.putExtra("mbid", queryToSearch.getText().toString());
        //Intent i = new Intent(this, SearchResultsActivity.class);

        startActivity(i);

        //mSearchResultAdapter.setRibots(artistSearches);
        //mSearchResultAdapter.notifyDataSetChanged();
    }

    @Override
    public void showNoResults() {
        // En caso de tener una lista de "cosas" sería necesario aquí vaciar el adapter
        // y decirle al adapter que se actualice
        animateUItoInitialState();
        Toast.makeText(this, R.string.empty_artists_or_songs, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showError() {
        animateUItoErrorState();
        DialogFactory.createGenericErrorDialog(this, getString(R.string.error_loading_artists))
                .show();
    }

    public void animateUItoLoadingState(){
        YoYo.with(Techniques.FadeIn).duration(500).playOn(spinner);
        spinner.setVisibility(View.VISIBLE);
        YoYo.with(Techniques.FadeOutUp).duration(300).playOn(logoIV);
        YoYo.with(Techniques.FadeOutDown).duration(300).playOn(queryToSearch);
    }

    public void animateUItoInitialState(){
        YoYo.with(Techniques.FadeOut).duration(500).playOn(spinner);
        spinner.setVisibility(View.GONE);
        YoYo.with(Techniques.FadeInDown).duration(300).playOn(logoIV);
        YoYo.with(Techniques.FadeInUp).duration(300).playOn(queryToSearch);
    }

    public void animateUItoErrorState(){
        YoYo.with(Techniques.Tada).duration(700).playOn(logoIV);
        animateUItoInitialState();
    }

}
