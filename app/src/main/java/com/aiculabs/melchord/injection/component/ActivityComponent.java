package com.aiculabs.melchord.injection.component;

import dagger.Subcomponent;
import com.aiculabs.melchord.injection.PerActivity;
import com.aiculabs.melchord.injection.module.ActivityModule;
import com.aiculabs.melchord.ui.artist.ArtistActivity;
import com.aiculabs.melchord.ui.release.ReleaseActivity;
import com.aiculabs.melchord.ui.search.SearchActivity;
import com.aiculabs.melchord.ui.searchResults.SearchResultsActivity;
import com.aiculabs.melchord.ui.song.SongActivity;
import com.aiculabs.melchord.ui.tab.TabActivity;

/**
 * This component inject dependencies to all Activities across the application
 */
@PerActivity
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(SearchActivity searchActivity);
    void inject(SearchResultsActivity searchResultsActivity);
    void inject(ArtistActivity artistActivity);
    void inject(ReleaseActivity releaseActivity);
    void inject(SongActivity songActivity);
    void inject(TabActivity tabActivity);
}
