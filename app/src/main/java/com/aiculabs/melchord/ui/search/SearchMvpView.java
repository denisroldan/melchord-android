package com.aiculabs.melchord.ui.search;

import com.aiculabs.melchord.data.model.ArtistSearch;
import com.aiculabs.melchord.ui.base.MvpView;

import java.util.List;

public interface SearchMvpView extends MvpView {

    void showResults(List<ArtistSearch> artistSearches);

    void showNoResults();

    void showError();

}
