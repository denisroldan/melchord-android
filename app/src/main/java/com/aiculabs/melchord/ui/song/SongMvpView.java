package com.aiculabs.melchord.ui.song;

import com.aiculabs.melchord.data.model.Song;
import com.aiculabs.melchord.ui.base.MvpView;

public interface SongMvpView extends MvpView {
    void showSong(Song song);
    void showNotabs();
    void showError();
}
