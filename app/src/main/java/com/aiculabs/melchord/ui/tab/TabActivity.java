package com.aiculabs.melchord.ui.tab;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.RelativeLayout;

import com.aiculabs.melchord.R;
import com.aiculabs.melchord.data.model.Tab;
import com.aiculabs.melchord.ui.base.BaseActivity;
import com.aiculabs.melchord.util.DialogFactory;

import javax.inject.Inject;

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
    private String mPlainHtml;
    private Integer mCurrentTraspose = 0;

    private Boolean mNightMode = true;

    private String mAccentColor = "#3dc1b6";
    private String mBackgroundColor = "#1a1a1a";
    private String mForegroundColor = "#fff";
    String[] mChordColors = {"#3dc1b6", "#ececec", "#E53935", "#000", "#fff"};
    Integer mSelectedColor = 0;


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
        mSelectedColor++;
        mSelectedColor = mSelectedColor % mChordColors.length;

        this.mPlainHtml = this.mPlainHtml.replace(".text-chord {color:" + mAccentColor + ";}",
                ".text-chord {color:" + mChordColors[mSelectedColor] + ";}");

        refreshWebViewContent();
        mAccentColor = mChordColors[mSelectedColor];
    }

    private void switchNightMode() {

        if (mNightMode) {
            // Switch to white
            this.mPlainHtml = this.mPlainHtml.replace("body{background-color: "
                            + mBackgroundColor + ";color: " + mForegroundColor + ";}",
                    "body{background-color: " + mForegroundColor + ";color: "
                            + mBackgroundColor + ";}");
        } else {
            // Switch to black
            this.mPlainHtml = this.mPlainHtml.replace("body{background-color: "
                            + mForegroundColor + ";color: " + mBackgroundColor + ";}",
                    "body{background-color: " + mBackgroundColor + ";color: "
                            + mForegroundColor + ";}");
        }

        refreshWebViewContent();
        mNightMode = !mNightMode;
    }


    private void showTrasposeToast() {
        TabToast.show(getApplicationContext(), getCurrentTraspose(), false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mTabPresenter.detachView();
    }

    @Override
    public void showTab(Tab tab) {
        this.mPlainHtml = tab.getContent();
        this.mPlainHtml = this.mPlainHtml.replace("html>", "html><style>.text-chord {color:"
                + mAccentColor + ";}body{background-color: " + mBackgroundColor + ";color: "
                + mForegroundColor + ";}</style>");
        refreshWebViewContent();

    }

    private void refreshWebViewContent() {
        tabWebView.loadDataWithBaseURL(null, this.mPlainHtml, "text/html", "utf-8", null);
    }

    @Override
    public void showError() {
        DialogFactory.createGenericErrorDialog(this, getString(R.string.error_loading_tab)).show();
    }

    public String getCurrentTraspose() {
        switch (this.mCurrentTraspose) {
            case 0:
                return "0";
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                return "+ " + Integer.toString(this.mCurrentTraspose);
            case -1:
            case -2:
            case -3:
            case -4:
            case -5:
            case -6:
                return Integer.toString(this.mCurrentTraspose);
            default:
                return "0";
        }

    }

    public void increaseTraspose() {
        if (this.mCurrentTraspose < 6) {
            this.mCurrentTraspose++;
            applyTraspose(true);
        }
    }

    public void decreaseTraspose() {
        if (this.mCurrentTraspose > -6) {
            this.mCurrentTraspose--;
            applyTraspose(false);
        }
    }

    public void applyTraspose(boolean positive) {

        String[] all_notes = {"C#", "D#", "F#", "G#", "A#", "Db", "Eb", "Gb", "Ab", "Bb", "C",
                "D", "E", "F", "G", "A", "B"};

        for (String note : all_notes) {
            String regex = "\\<span class=\'text-chord\'\\>" + note + "(\\w*)\\<\\/span\\>";
            String new_note = "";
            if (positive) {
                new_note = getNextChromaticNote(note);
            } else {
                new_note = getPriorChromaticNote(note);
            }
            this.mPlainHtml = this.mPlainHtml.replaceAll(regex,
                    "<span class='text-chord trasposed'>" + new_note + "$1</span>");
        }

        String post_regex = "\\<span class=\'text-chord trasposed\'\\>(\\w*)\\<\\/span\\>";
        this.mPlainHtml = this.mPlainHtml.replaceAll(post_regex,
                "<span class='text-chord'>$1</span>");
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
