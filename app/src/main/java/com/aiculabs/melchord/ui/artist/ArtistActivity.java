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
    //@Inject ArtistAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist);
        getActivityComponent().inject(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        presenter.attachView(this);
        String mbid = getIntent().getStringExtra("mbid");
        presenter.getData("9c9f1380-2516-4fc9-a3e6-f9f61941d090");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    @Override
    public void showArtist(Artist artist) {
        DialogFactory.createSimpleOkErrorDialog(this, "FUNSIONA", "El hartista hamijísimo se llama " + artist.getName() + " y es de " + artist.getCountry()).show();
    }

    @Override
    public void showError() {
        DialogFactory.createGenericErrorDialog(this, getString(R.string.error_loading_artist)).show();
    }
}
