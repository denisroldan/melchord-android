package com.aiculabs.melchord.ui.search;

import java.util.List;

import com.aiculabs.melchord.data.model.ArtistSearch;
import com.aiculabs.melchord.ui.base.MvpView;

public interface SearchMvpView extends MvpView {

    void showResults(List<ArtistSearch> artistSearches);

    void showNoResults();

    void showError();

}
