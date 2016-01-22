package com.aiculabs.melchord.ui.song;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.aiculabs.melchord.R;
import com.aiculabs.melchord.data.model.Song;
import com.aiculabs.melchord.ui.base.BaseActivity;
import com.aiculabs.melchord.util.DialogFactory;

import javax.inject.Inject;

public class SongActivity extends BaseActivity implements SongMvpView {
    @Inject SongPresenter mSongPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);
        getActivityComponent().inject(this);
        //  Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        mSongPresenter.attachView(this);
        String mbid = getIntent().getStringExtra("mbid");
        mSongPresenter.getData("2eda1b02-c4f8-46ef-8d6f-0bd6998fc6cc");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSongPresenter.detachView();
    }

    @Override
    public void showSong(Song song) {
        DialogFactory.createSimpleOkErrorDialog(this, "FUNSIONA", "El título de la cansión es " + song.getTitle());
    }

    @Override
    public void showError() {
        DialogFactory.createGenericErrorDialog(this, getString(R.string.error_loading_song)).show();
    }
}
