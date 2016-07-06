package com.aiculabs.melchord.ui.artist;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.aiculabs.melchord.R;
import com.aiculabs.melchord.data.model.Artist;
import com.aiculabs.melchord.data.model.Release;
import com.aiculabs.melchord.ui.base.BaseActivity;
import com.aiculabs.melchord.ui.release.ReleaseActivity;
import com.aiculabs.melchord.ui.release.ReleaseConstants;
import com.aiculabs.melchord.util.CustomItemClickListener;
import com.aiculabs.melchord.util.DialogFactory;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class ArtistActivity extends BaseActivity implements ArtistMvpView {
    @Inject
    ArtistPresenter mArtistPresenter;
    private ArtistAdapter mArtistAdapter;
    private Artist mArtist;

    private RecyclerView.LayoutManager mLayoutManager;

    String title, mbid, image_url, comment, name;


    @BindView(R.id.releases_recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.artist_toolbar)
    Toolbar artist_Toolbar;


    @BindView(R.id.artist_toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;

    @BindView(R.id.artist_backdrop)
    ImageView backdrop;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent().inject(this);
        setContentView(R.layout.activity_artist);
        ButterKnife.bind(this);

        mArtistAdapter = new ArtistAdapter(new CustomItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Intent i = new Intent(getBaseContext(), ReleaseActivity.class);
                i.putExtra(ReleaseConstants.RELEASE_INTENT_MBID_TAG, mArtist.getReleaseSet().get(position).getMbid());
                startActivity(i);
            }
        });

        mLayoutManager = new GridLayoutManager(this, 2);
        mLayoutManager.setAutoMeasureEnabled(true);

        mRecyclerView.setAdapter(mArtistAdapter);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);

        mArtistPresenter.attachView(this);

        setSupportActionBar(artist_Toolbar);

        Intent intent = getIntent();
        mbid = intent.getStringExtra(ArtistConstants.ARTIST_INTENT_MBID_TAG);
        name = intent.getStringExtra(ArtistConstants.ARTIST_INTENT_NAME_TAG);
        comment = intent.getStringExtra(ArtistConstants.ARTIST_INTENT_COMMENT_TAG);
        title = getString(R.string.loading_artist_title);
        Timber.wtf(mbid);
        refreshUI();

        mArtistPresenter.getData(mbid);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mArtistPresenter.detachView();
    }

    @Override
    public void showArtist(Artist artist) {
        mArtist = artist;
        title = artist.getName();
        if (artist.getLargeImage() != null) image_url = artist.getLargeImage();
        refreshUI();

        List<Release> filteredReleases = new ArrayList<>();
        for (Release release : artist.getReleaseSet()) {
            if (release.getType().equals("Album")) {
                filteredReleases.add(release);
            }
        }
        artist.setReleaseSet(filteredReleases);
        mArtistAdapter.setReleases(artist.getReleaseSet());
        mArtistAdapter.notifyDataSetChanged();
    }

    @Override
    public void emptyArtist() {
        Toast.makeText(this, R.string.empty_artist, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showError() {
        DialogFactory.createGenericErrorDialog(this, getString(R.string.error_loading_artist)).show();
    }

    private void refreshUI() {
        toolbarLayout.setTitle(title);
        backdrop.setColorFilter(Color.argb(30, 0, 0, 0));
        Glide.with(this).load(image_url).error(R.drawable.bg).into(backdrop);
    }


}
