package com.aiculabs.melchord.ui.release;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.aiculabs.melchord.R;
import com.aiculabs.melchord.data.model.Release;
import com.aiculabs.melchord.ui.base.BaseActivity;
import com.aiculabs.melchord.util.DialogFactory;
import com.squareup.picasso.Picasso;

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

    @Bind (R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;

    @Bind (R.id.backdrop)
    ImageView backdrop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivityComponent().inject(this);
        setContentView(R.layout.activity_release);
        ButterKnife.bind(this);
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
    }

    @Override
    public void showNoResults() {

    }

    @Override
    public void showError() {
        DialogFactory.createGenericErrorDialog(this, getString(R.string.error_loading_artists))
                .show();
    }

    private void refreshUI() {
        toolbarLayout.setTitle(title);
        backdrop.setColorFilter(Color.argb(30, 0, 0, 0));
        Picasso.with(this).load(image_url).error(R.drawable.bg).into(backdrop);
    }

}
