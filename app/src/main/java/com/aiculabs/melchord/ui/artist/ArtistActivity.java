package com.aiculabs.melchord.ui.artist;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.aiculabs.melchord.R;
import com.aiculabs.melchord.data.model.Artist;
import com.aiculabs.melchord.ui.base.BaseActivity;
import com.aiculabs.melchord.util.DialogFactory;

import java.util.List;

import javax.inject.Inject;

public class ArtistActivity extends BaseActivity implements ArtistMvpView{

    @Inject ArtistPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist);
//        getActivityComponent().inject(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        presenter.attachView(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    @Override
    public void showResults(List<Artist> artistSearches) {

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
