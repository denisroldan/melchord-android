package com.aiculabs.melchord.ui.release;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.aiculabs.melchord.R;
import com.aiculabs.melchord.data.model.Release;
import com.aiculabs.melchord.ui.base.BaseActivity;

import java.util.List;

import javax.inject.Inject;

public class ReleaseActivity extends BaseActivity implements ReleaseMvpView {

    @Inject
    ReleasePresenter mReleasePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivityComponent().inject(this);
        mReleasePresenter.attachView(this);
        setContentView(R.layout.activity_release);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mReleasePresenter.detachView();
    }

    @Override
    public void showResults(List<Release> releaseList) {

    }

    @Override
    public void showNoResults() {

    }

    @Override
    public void showError() {

    }
}
