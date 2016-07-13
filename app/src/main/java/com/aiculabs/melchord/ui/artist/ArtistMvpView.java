package com.aiculabs.melchord.ui.artist;

import com.aiculabs.melchord.data.model.Artist;
import com.aiculabs.melchord.ui.base.MvpView;

public interface ArtistMvpView extends MvpView {
    void showArtist(Artist artist);
    void emptyArtist();
    void showError();
}
