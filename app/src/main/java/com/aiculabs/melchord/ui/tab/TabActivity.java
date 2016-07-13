package com.aiculabs.melchord.ui.tab;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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

import java.util.ArrayList;
import java.util.Map;
import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.championswimmer.sfg.lib.SimpleFingerGestures;

public class TabActivity extends BaseActivity implements TabMvpView {

    @Inject
    TabPresenter mTabPresenter;

    @BindView(R.id.tab_webView)
    WebView tabWebView;

    @BindView(R.id.tab_relativeLayout)
    RelativeLayout tab_relativeLayout;
    private String plain_html;
    private Integer current_traspose = 0;

    private Boolean nightMode = true;

    private String accent_color = "#3dc1b6";
    private String background_color = "#1a1a1a";
    private String foreground_color = "#fff";
    String chord_colors[] = {"#3dc1b6", "#ececec", "#E53935", "#000", "#fff"};
    Integer selected_color = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent().inject(this);
        setContentView(R.layout.activity_tab);
        ButterKnife.bind(this);
        mTabPresenter.attachView(this);
        mTabPresenter.getData(getIntent().getIntExtra("id", 0));

        SimpleFingerGestures sfg = new SimpleFingerGestures();
        sfg.setConsumeTouchEvents(false);

        sfg.setOnFingerGestureListener(new SimpleFingerGestures.OnFingerGestureListener() {
            @Override
            public boolean onSwipeUp(int fingers, long gestureDuration, double gestureDistance) {
                if (fingers == 2) {
                    increaseTraspose();
                    showTrasposeToast();
                    return true;
                }
                return false;
            }

            @Override
            public boolean onSwipeDown(int fingers, long gestureDuration, double gestureDistance) {
                if (fingers == 2) {
                    decreaseTraspose();
                    showTrasposeToast();
                    return true;
                }
                return false;
            }

            @Override
            public boolean onSwipeLeft(int fingers, long gestureDuration, double gestureDistance) {
                return false;
            }

            @Override
            public boolean onSwipeRight(int fingers, long gestureDuration, double gestureDistance) {
                return false;
            }

            @Override
            public boolean onPinch(int fingers, long gestureDuration, double gestureDistance) {
                return false;
            }

            @Override
            public boolean onUnpinch(int fingers, long gestureDuration, double gestureDistance) {
                return false;
            }

            @Override
            public boolean onDoubleTap(int fingers) {
                return false;
            }
        });

        tabWebView.setOnTouchListener(sfg);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_tab, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_chord_color:
                switchChordsColor();
                return true;

            case R.id.action_night_mode:
                switchNightMode();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }

    private void switchChordsColor() {
        selected_color++;
        selected_color = selected_color % chord_colors.length;

        this.plain_html = this.plain_html.replace(".text-chord {color:" + accent_color + ";}",
                ".text-chord {color:" + chord_colors[selected_color] + ";}");

        refreshWebViewContent();
        accent_color = chord_colors[selected_color];
    }

    private void switchNightMode() {

        if (nightMode) {
            // Switch to white
            this.plain_html = this.plain_html.replace("body{background-color: " + background_color + ";color: " + foreground_color + ";}",
                    "body{background-color: " + foreground_color + ";color: " + background_color + ";}");
        } else {
            // Switch to black
            this.plain_html = this.plain_html.replace("body{background-color: " + foreground_color + ";color: " + background_color + ";}",
                    "body{background-color: " + background_color + ";color: " + foreground_color + ";}");
        }

        refreshWebViewContent();
        nightMode = !nightMode;
    }


    private void showTrasposeToast() {
        TabToast.show(getApplicationContext(), get_current_traspose(), false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mTabPresenter.detachView();
    }

    @Override
    public void showTab(Tab tab) {
        this.plain_html = tab.getContent();
        this.plain_html = this.plain_html.replace("html>", "html><style>.text-chord {color:" + accent_color + ";}body{background-color: " + background_color + ";color: " + foreground_color + ";}</style>");
        refreshWebViewContent();

    }

    private void refreshWebViewContent() {
        tabWebView.loadDataWithBaseURL(null, this.plain_html, "text/html", "utf-8", null);
    }

    @Override
    public void showError() {
        DialogFactory.createGenericErrorDialog(this, getString(R.string.error_loading_tab)).show();
    }

    public String get_current_traspose() {
        switch (this.current_traspose) {
            case 0:
                return "0";
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                return "+ " + Integer.toString(this.current_traspose);
            case -1:
            case -2:
            case -3:
            case -4:
            case -5:
            case -6:
                return Integer.toString(this.current_traspose);
            default:
                return "0";
        }

    }

    public void increaseTraspose() {
        if (this.current_traspose < 6) {
            this.current_traspose++;
            applyTraspose(true);
        }
    }

    public void decreaseTraspose() {
        if (this.current_traspose > -6) {
            this.current_traspose--;
            applyTraspose(false);
        }
    }

    public void applyTraspose(boolean positive) {

        String[] all_notes = {"C#", "D#", "F#", "G#", "A#", "Db", "Eb", "Gb", "Ab", "Bb", "C", "D", "E", "F", "G", "A", "B"};

        for (String note : all_notes) {
            String regex = "\\<span class=\'text-chord\'\\>" + note + "(\\w*)\\<\\/span\\>";
            String new_note = "";
            if (positive) {
                new_note = getNextChromaticNote(note);
            } else {
                new_note = getPriorChromaticNote(note);
            }
            this.plain_html = this.plain_html.replaceAll(regex, "<span class='text-chord trasposed'>" + new_note + "$1</span>");
        }

        String post_regex = "\\<span class=\'text-chord trasposed\'\\>(\\w*)\\<\\/span\\>";
        this.plain_html = this.plain_html.replaceAll(post_regex, "<span class='text-chord'>$1</span>");
        refreshWebViewContent();
    }

    private String getNextChromaticNote(String note) {
        if (note.equals("C#") || note.equals("Db")) {
            return "D";
        }
        if (note.equals("D#") || note.equals("Eb")) {
            return "E";
        }
        if (note.equals("F#") || note.equals("Gb")) {
            return "G";
        }
        if (note.equals("G#") || note.equals("Ab")) {
            return "A";
        }
        if (note.equals("A#") || note.equals("Bb")) {
            return "B";
        }

        if (note.equals("C")) {
            return "C#";
        }
        if (note.equals("D")) {
            return "D#";
        }
        if (note.equals("E")) {
            return "F";
        }
        if (note.equals("F")) {
            return "F#";
        }
        if (note.equals("G")) {
            return "G#";
        }
        if (note.equals("A")) {
            return "A#";
        }
        if (note.equals("B")) {
            return "C";
        }
        return "";
    }

    private String getPriorChromaticNote(String note) {
        if (note.equals("C#") || note.equals("Db")) {
            return "C";
        }
        if (note.equals("D#") || note.equals("Eb")) {
            return "D";
        }
        if (note.equals("F#") || note.equals("Gb")) {
            return "F";
        }
        if (note.equals("G#") || note.equals("Ab")) {
            return "G";
        }
        if (note.equals("A#") || note.equals("Bb")) {
            return "A";
        }

        if (note.equals("C")) {
            return "B";
        }
        if (note.equals("D")) {
            return "C#";
        }
        if (note.equals("E")) {
            return "D#";
        }
        if (note.equals("F")) {
            return "E";
        }
        if (note.equals("G")) {
            return "F#";
        }
        if (note.equals("A")) {
            return "G#";
        }
        if (note.equals("B")) {
            return "A#";
        }
        return "";
    }
}
