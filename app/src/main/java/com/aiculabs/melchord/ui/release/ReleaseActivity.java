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
import android.view.WindowManager;
import android.widget.ImageView;

import com.aiculabs.melchord.R;
import com.aiculabs.melchord.data.model.Release;
import com.aiculabs.melchord.data.model.Song;
import com.aiculabs.melchord.ui.base.BaseActivity;
import com.aiculabs.melchord.util.DialogFactory;
import com.aiculabs.melchord.util.MyLinearLayoutManager;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;

public class ReleaseActivity extends BaseActivity implements ReleaseMvpView {

    String title, mbid, image_url;

    @Inject
    ReleasePresenter mReleasePresenter;
    @Inject ReleaseAdapter mReleaseAdapter;

    @Bind (R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;

    @Bind (R.id.backdrop)
    ImageView backdrop;

    @Bind(R.id.release_recycler_view)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivityComponent().inject(this);
        setContentView(R.layout.activity_release);
        ButterKnife.bind(this);

        mRecyclerView.setAdapter(mReleaseAdapter);
        final MyLinearLayoutManager layoutManager = new MyLinearLayoutManager(this, LinearLayoutManager.VERTICAL, false, getScreenHeight(this));
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setHasFixedSize(false);
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
    public void showResults(Release release) {
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
    }

    @Override
    public void showError() {
        DialogFactory.createGenericErrorDialog(this, getString(R.string.error_loading_release))
                .show();
    }

    private void refreshUI() {
        toolbarLayout.setTitle(title);
        backdrop.setColorFilter(Color.argb(30, 0, 0, 0));
        Picasso.with(this).load(image_url).error(R.drawable.bg).into(backdrop);
    }

    private int getScreenHeight(Context context) {
        int measuredHeight;
        Point size = new Point();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            wm.getDefaultDisplay().getSize(size);
            measuredHeight = size.y;
        } else {
            Display d = wm.getDefaultDisplay();
            measuredHeight = d.getHeight();
        }

        return measuredHeight;
    }

}
