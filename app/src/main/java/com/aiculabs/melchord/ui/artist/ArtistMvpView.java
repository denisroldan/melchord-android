package com.aiculabs.melchord.ui.artist;

import com.aiculabs.melchord.data.model.Artist;
import com.aiculabs.melchord.ui.base.MvpView;

import java.util.List;

/**
 * Created by Also on 21/1/16.
 */
public interface ArtistMvpView extends MvpView {
    void showArtist(Artist artist);
    void showError();

}
