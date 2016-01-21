package com.aiculabs.melchord.ui.searchResults;

import com.aiculabs.melchord.data.model.ArtistSearch;
import com.aiculabs.melchord.ui.base.MvpView;

import java.util.List;

public interface SearchResultsMvpView extends MvpView {

    void showResults(List<ArtistSearch> artistSearches);

    void showNoResults();

    void showError();

}
