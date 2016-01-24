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
    @Inject
    TabPresenter mTabPresenter;

    @Bind(R.id.tab_webView)
    WebView tabWebView;

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
        String plain_html = tab.getContent();
        String accent_color = "#3dc1b6";
        String background_color = "#1a1a1a";
        String foreground_color = "#fff";
        plain_html = plain_html.replace("html>", "html><style>.text-chord {color:" + accent_color + ";}body{background-color: " + background_color + ";color: " + foreground_color + ";}</style>");
        tabWebView.loadDataWithBaseURL(null, plain_html, "text/html", "utf-8", null);

    }

    @Override
    public void showError() {
        DialogFactory.createGenericErrorDialog(this, getString(R.string.error_loading_tab)).show();
    }
}
