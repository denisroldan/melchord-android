package com.aiculabs.melchord.ui.release;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.aiculabs.melchord.R;
import com.aiculabs.melchord.data.model.Release;
import com.aiculabs.melchord.data.model.Song;
import com.aiculabs.melchord.ui.base.BaseActivity;
import com.aiculabs.melchord.ui.song.SongActivity;
import com.aiculabs.melchord.util.CustomItemClickListener;
import com.aiculabs.melchord.util.DialogFactory;
import com.bumptech.glide.Glide;

import java.util.Collections;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReleaseActivity extends BaseActivity implements ReleaseMvpView {

    String title, mbid, image_url;

    private RecyclerView.LayoutManager mLayoutManager;


    @Inject
    ReleasePresenter mReleasePresenter;
    private ReleaseAdapter mReleaseAdapter;
    private Release mRelease;

    @BindView (R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;

    @BindView (R.id.backdrop)
    ImageView backdrop;

    @BindView(R.id.songs_recycler_view)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent().inject(this);
        setContentView(R.layout.activity_release);
        ButterKnife.bind(this);

        mReleaseAdapter = new ReleaseAdapter(new CustomItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Intent i = new Intent(getBaseContext(), SongActivity.class);
                i.putExtra("mbid", mRelease.getSongSet().get(position).getMbid());
                startActivity(i);
            }
        });

        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setAutoMeasureEnabled(true);


        mRecyclerView.setAdapter(mReleaseAdapter);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mReleasePresenter.attachView(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        mbid = intent.getStringExtra(ReleaseConstants.RELEASE_INTENT_MBID_TAG);
        title = getString(R.string.loading_release_text);
        refreshUI();

        mReleasePresenter.getRelease(mbid);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mReleasePresenter.detachView();
    }

    @Override
    public void showRelease(Release release) {
        mRelease = release;
        title = release.getTitle();
        if (release.getLargeImage() != null) image_url = release.getLargeImage();
        refreshUI();
        mReleaseAdapter.setSongs(release.getSongSet());
        mReleaseAdapter.notifyDataSetChanged();
    }

    @Override
    public void showNoResults() {
        mReleaseAdapter.setSongs(Collections.<Song>emptyList());
        mReleaseAdapter.notifyDataSetChanged();
        Toast.makeText(this, R.string.empty_release, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showError() {
        DialogFactory.createGenericErrorDialog(this, getString(R.string.error_loading_release))
                .show();
    }

    private void refreshUI() {
        toolbarLayout.setTitle(title);
        backdrop.setColorFilter(Color.argb(30, 0, 0, 0));
        Glide.with(this).load(image_url).error(R.drawable.bg).into(backdrop);
    }

}
