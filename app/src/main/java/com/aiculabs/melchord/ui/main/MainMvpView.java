package com.aiculabs.melchord.ui.main;

import java.util.List;

import com.aiculabs.melchord.data.model.Ribot;
import com.aiculabs.melchord.ui.base.MvpView;

public interface MainMvpView extends MvpView {

    void showRibots(List<Ribot> ribots);

    void showRibotsEmpty();

    void showError();

}
