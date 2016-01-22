package com.aiculabs.melchord.ui.artist;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.aiculabs.melchord.R;
import com.aiculabs.melchord.data.model.Artist;
import com.aiculabs.melchord.ui.base.BaseActivity;
import com.aiculabs.melchord.util.DialogFactory;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ArtistActivity extends BaseActivity implements ArtistMvpView{

    @Inject ArtistPresenter mArtistPresenter;
    @Inject ArtistAdapter mArtistAdapter;

    @Bind(R.id.artist_top_image_view) ImageView artistImageView;
    @Bind(R.id.releases_recycler_view) RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivityComponent().inject(this);
        setContentView(R.layout.activity_artist);
        ButterKnife.bind(this);

        mRecyclerView.setAdapter(mArtistAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mArtistPresenter.attachView(this);

        String mbid = getIntent().getStringExtra("mbid");
        mArtistPresenter.getData("9c9f1380-2516-4fc9-a3e6-f9f61941d090");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mArtistPresenter.detachView();
    }

    @Override
    public void showArtist(Artist artist) {
        mArtistAdapter.setReleases(artist.getReleaseSet());
        mArtistAdapter.notifyDataSetChanged();
        Picasso.with(this).load(artist.getLargeImage()).error(R.drawable.bg).into(artistImageView);
    }

    @Override
    public void showError() {
        DialogFactory.createGenericErrorDialog(this, getString(R.string.error_loading_artist)).show();
    }
}
