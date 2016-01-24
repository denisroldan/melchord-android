package com.aiculabs.melchord.ui.artist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.aiculabs.melchord.R;
import com.aiculabs.melchord.data.model.Artist;
import com.aiculabs.melchord.data.model.Release;
import com.aiculabs.melchord.ui.base.BaseActivity;
import com.aiculabs.melchord.ui.release.ReleaseActivity;
import com.aiculabs.melchord.util.CustomItemClickListener;
import com.aiculabs.melchord.util.DialogFactory;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ArtistActivity extends BaseActivity implements ArtistMvpView{

    @Inject ArtistPresenter mArtistPresenter;
    private ArtistAdapter mArtistAdapter;
    private Artist mArtist;

    @Bind(R.id.artist_top_image_view) ImageView artistImageView;
    @Bind(R.id.releases_recycler_view) RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivityComponent().inject(this);
        setContentView(R.layout.activity_artist);
        ButterKnife.bind(this);

        mArtistAdapter = new ArtistAdapter(new CustomItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Intent i = new Intent(getBaseContext(), ReleaseActivity.class);
                i.putExtra("mbid", mArtist.getReleaseSet().get(position).getMbid());
                startActivity(i);
            }
        });

        mRecyclerView.setAdapter(mArtistAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mArtistPresenter.attachView(this);
        String mbid = getIntent().getStringExtra("mbid");
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
        List<Release> filteredReleases = new ArrayList<>();
        for (Release release: artist.getReleaseSet()) {
            if (release.getType().equals("Album")) {
                filteredReleases.add(release);
            }
        }
        artist.setReleaseSet(filteredReleases);
        mArtistAdapter.setReleases(artist.getReleaseSet());
        mArtistAdapter.notifyDataSetChanged();
        Picasso.with(this).load(artist.getLargeImage()).error(R.drawable.bg).into(artistImageView);
    }

    @Override
    public void showError() {
        DialogFactory.createGenericErrorDialog(this, getString(R.string.error_loading_artist)).show();
    }
}
