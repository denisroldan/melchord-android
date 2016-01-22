package com.aiculabs.melchord.ui.searchResults;

import com.aiculabs.melchord.ui.base.MvpView;

import java.util.ArrayList;
import java.util.HashMap;

public interface SearchResultsMvpView extends MvpView {

    void showResults(ArrayList<HashMap<String, String>> results);
}
