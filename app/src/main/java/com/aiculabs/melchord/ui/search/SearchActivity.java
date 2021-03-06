package com.aiculabs.melchord.ui.search;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.aiculabs.melchord.R;
import com.aiculabs.melchord.data.model.ArtistSearch;
import com.aiculabs.melchord.ui.artist.ArtistActivity;
import com.aiculabs.melchord.ui.artist.ArtistConstants;
import com.aiculabs.melchord.ui.base.BaseActivity;
import com.aiculabs.melchord.ui.search_results.SearchResultsActivity;
import com.aiculabs.melchord.util.DialogFactory;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends BaseActivity implements SearchMvpView {

    @Inject
    SearchPresenter mSearchPresenter;

    @BindView(R.id.queryEditText)
    EditText queryToSearch;
    @BindView(R.id.progressBar)
    ProgressBar spinner;
    @BindView(R.id.logo)
    ImageView logoIV;
    @BindView(R.id.tab_coordinator_layout)
    CoordinatorLayout tabCoordinator;

    @OnClick(R.id.fab)
    void searchBtnPushed() {
        if (queryToSearch.getText().toString().length() == 0) {
            animateUItoErrorState();

            DialogFactory.createGenericErrorDialog(this, R.string.really_question)
                    .show();
            return;
        }
        animateUItoLoadingState();
        mSearchPresenter.search(queryToSearch.getText().toString());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent().inject(this);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        mSearchPresenter.attachView(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.search_toolbar);

        setSupportActionBar(toolbar);

        queryToSearch.setImeOptions(EditorInfo.IME_ACTION_DONE);
        queryToSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER))
                        || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    searchBtnPushed();
                }
                return false;
            }
        });
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

        ArrayList<HashMap<String, String>> results = new ArrayList<>();

        Intent i = new Intent(this, SearchResultsActivity.class);
        for (ArtistSearch artistSearchResult : artistSearches) {
            HashMap<String, String> artistSearch = new HashMap<>();
            artistSearch.put(ArtistConstants.ARTIST_INTENT_NAME_TAG, artistSearchResult.getName());
            artistSearch.put(ArtistConstants.ARTIST_INTENT_MBID_TAG, artistSearchResult.getId());
            artistSearch.put(ArtistConstants.ARTIST_INTENT_COMMENT_TAG,
                    artistSearchResult.getComment());
            results.add(artistSearch);
        }


        if (results.size() == 0) {
            Toast.makeText(this, R.string.empty_artist, Toast.LENGTH_LONG).show();
        } else if (results.size() == 1) {
            Intent i2 = new Intent(this, ArtistActivity.class);
            i2.putExtra("mbid", results.get(0).get("mbid"));
            startActivity(i2);
        } else {
            i.putExtra("search_results", results);
            startActivity(i);
        }


    }

    @Override
    public void showNoResults() {
        // En caso de tener una lista de "cosas" sería necesario aquí vaciar el adapter
        // y decirle al adapter que se actualice
        animateUItoInitialState();
        Toast.makeText(this, R.string.empty_search_results, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showError() {
        animateUItoErrorState();
        DialogFactory.createGenericErrorDialog(this,
                getString(R.string.error_loading_search_results))
                .show();
    }

    public void animateUItoLoadingState() {
        YoYo.with(Techniques.FadeIn).duration(500).playOn(spinner);
        spinner.setVisibility(View.VISIBLE);
        YoYo.with(Techniques.FadeOutUp).duration(300).playOn(logoIV);
        YoYo.with(Techniques.FadeOutDown).duration(300).playOn(queryToSearch);
    }

    public void animateUItoInitialState() {
        YoYo.with(Techniques.FadeOut).duration(500).playOn(spinner);
        spinner.setVisibility(View.GONE);
        YoYo.with(Techniques.FadeInDown).duration(300).playOn(logoIV);
        YoYo.with(Techniques.FadeInUp).duration(300).playOn(queryToSearch);
    }

    public void animateUItoErrorState() {
        YoYo.with(Techniques.Tada).duration(700).playOn(logoIV);
        animateUItoInitialState();
    }

}
