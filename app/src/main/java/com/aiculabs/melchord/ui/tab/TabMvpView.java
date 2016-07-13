package com.aiculabs.melchord.ui.tab;

import com.aiculabs.melchord.data.model.Tab;
import com.aiculabs.melchord.ui.base.MvpView;

public interface TabMvpView extends MvpView{
    void showTab(Tab tab);
    void showError();

}
