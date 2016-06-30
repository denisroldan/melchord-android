package com.aiculabs.melchord.ui.tab;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aiculabs.melchord.R;
import com.aiculabs.melchord.data.model.Tab;
import com.aiculabs.melchord.ui.base.BaseActivity;
import com.aiculabs.melchord.util.DialogFactory;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TabActivity extends BaseActivity implements TabMvpView {

    int GLOBAL_TOUCH_POSITION_X = 0;
    int GLOBAL_TOUCH_CURRENT_POSITION_X = 0;

    @Inject
    TabPresenter mTabPresenter;

    @BindView(R.id.tab_webView)
    WebView tabWebView;

    @BindView(R.id.tab_relativeLayout)
    RelativeLayout tab_relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent().inject(this);
        setContentView(R.layout.activity_tab);
        ButterKnife.bind(this);
        mTabPresenter.attachView(this);
        mTabPresenter.getData(getIntent().getIntExtra("id", 0));

        //Two-Finger Drag Gesture detection
        tab_relativeLayout.setOnTouchListener(
                new RelativeLayout.OnTouchListener(){
                    @Override
                    public boolean onTouch(View v, MotionEvent m) {
                        handleTouch(m);
                        return true;
                    }
                });
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

    void handleTouch(MotionEvent m){
        //Number of touches
        int pointerCount = m.getPointerCount();
        if(pointerCount == 2){
            int action = m.getActionMasked();
            int actionIndex = m.getActionIndex();
            String actionString;
            switch (action)
            {
                case MotionEvent.ACTION_DOWN:
                    GLOBAL_TOUCH_POSITION_X = (int) m.getX(1);
                    actionString = "DOWN"+" current "+GLOBAL_TOUCH_CURRENT_POSITION_X+" prev "+GLOBAL_TOUCH_POSITION_X;
                    break;
                case MotionEvent.ACTION_UP:
                    GLOBAL_TOUCH_CURRENT_POSITION_X = 0;
                    actionString = "UP"+" current "+GLOBAL_TOUCH_CURRENT_POSITION_X+" prev "+GLOBAL_TOUCH_POSITION_X;
                    break;
                case MotionEvent.ACTION_MOVE:
                    GLOBAL_TOUCH_CURRENT_POSITION_X = (int) m.getX(1);
                    int diff = GLOBAL_TOUCH_POSITION_X-GLOBAL_TOUCH_CURRENT_POSITION_X;
                    actionString = "Diff "+diff+" current "+GLOBAL_TOUCH_CURRENT_POSITION_X+" prev "+GLOBAL_TOUCH_POSITION_X;
                    break;
                case MotionEvent.ACTION_POINTER_DOWN:
                    GLOBAL_TOUCH_POSITION_X = (int) m.getX(1);
                    actionString = "DOWN"+" current "+GLOBAL_TOUCH_CURRENT_POSITION_X+" prev "+GLOBAL_TOUCH_POSITION_X;
                    break;
                default:
                    actionString = "";
            }

            if (!actionString.equals("")){
                TabToast.show(this, actionString, true);
            }

            pointerCount = 0;
        }
        else {
            GLOBAL_TOUCH_POSITION_X = 0;
            GLOBAL_TOUCH_CURRENT_POSITION_X = 0;
        }
    }
}
