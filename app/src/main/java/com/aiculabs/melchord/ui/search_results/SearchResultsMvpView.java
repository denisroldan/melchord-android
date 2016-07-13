package com.aiculabs.melchord.ui.search_results;

import com.aiculabs.melchord.ui.base.MvpView;

import java.util.ArrayList;
import java.util.HashMap;

public interface SearchResultsMvpView extends MvpView {

    void showResults(ArrayList<HashMap<String, String>> results);
}
