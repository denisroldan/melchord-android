package com.aiculabs.melchord.ui.song;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.aiculabs.melchord.R;
import com.aiculabs.melchord.data.model.Song;
import com.aiculabs.melchord.ui.base.BaseActivity;
import com.aiculabs.melchord.ui.tab.TabActivity;
import com.aiculabs.melchord.util.CustomItemClickListener;
import com.aiculabs.melchord.util.DialogFactory;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SongActivity extends BaseActivity implements SongMvpView {
    @Inject SongPresenter mSongPresenter;
    private SongAdapter mSongAdapter;
    private Song mSong;

    @Bind(R.id.tabs_recycler_view) RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivityComponent().inject(this);
        setContentView(R.layout.activity_song);
        ButterKnife.bind(this);

        mSongAdapter = new SongAdapter(new CustomItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Intent i = new Intent(getBaseContext(), TabActivity.class);
                i.putExtra("id", mSong.getTabSet().get(position).getId());
                startActivity(i);
            }
        });

        mRecyclerView.setAdapter(mSongAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mSongPresenter.attachView(this);
        mSongPresenter.getData(getIntent().getStringExtra("mbid"));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSongPresenter.detachView();
    }

    @Override
    public void showSong(Song song) {
        mSong = song;
        mSongAdapter.setTabs(song.getTabSet());
        mSongAdapter.notifyDataSetChanged();
    }

    @Override
    public void showNotabs() {
        //animateUItoInitialState();
        Toast.makeText(this, R.string.message_no_tabs, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showError() {
        DialogFactory.createGenericErrorDialog(this, getString(R.string.error_loading_song)).show();
    }
}
