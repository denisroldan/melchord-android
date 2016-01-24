package com.aiculabs.melchord.ui.tab;

import android.os.Bundle;
import android.webkit.WebView;

import com.aiculabs.melchord.R;
import com.aiculabs.melchord.data.model.Tab;
import com.aiculabs.melchord.ui.base.BaseActivity;
import com.aiculabs.melchord.util.DialogFactory;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TabActivity extends BaseActivity implements TabMvpView {
    @Inject TabPresenter mTabPresenter;

    @Bind(R.id.tab_webView) WebView tabWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivityComponent().inject(this);
        setContentView(R.layout.activity_tab);
        ButterKnife.bind(this);
        mTabPresenter.attachView(this);
        mTabPresenter.getData(getIntent().getIntExtra("id", 0));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mTabPresenter.detachView();
    }

    @Override
    public void showTab(Tab tab) {
        tabWebView.loadDataWithBaseURL(null, tab.getContent(), "text/html", "utf-8", null);
    }

    @Override
    public void showError() {
        DialogFactory.createGenericErrorDialog(this, getString(R.string.error_loading_tab)).show();
    }
}
