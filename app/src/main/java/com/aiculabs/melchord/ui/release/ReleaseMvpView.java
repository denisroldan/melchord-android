package com.aiculabs.melchord.ui.release;

import com.aiculabs.melchord.data.model.Release;
import com.aiculabs.melchord.ui.base.MvpView;

import java.util.List;

public interface ReleaseMvpView extends MvpView {
    void showResults(Release release);

    void showNoResults();

    void showError();

}
