package com.aiculabs.melchord.ui.release;

import com.aiculabs.melchord.data.model.Release;
import com.aiculabs.melchord.ui.base.MvpView;

public interface ReleaseMvpView extends MvpView {
    void showRelease(Release release);

    void showNoResults();

    void showError();

}
