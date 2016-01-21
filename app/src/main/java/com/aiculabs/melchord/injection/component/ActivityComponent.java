package com.aiculabs.melchord.injection.component;

import dagger.Component;
import com.aiculabs.melchord.injection.PerActivity;
import com.aiculabs.melchord.injection.module.ActivityModule;
import com.aiculabs.melchord.ui.artist.ArtistActivity;
import com.aiculabs.melchord.ui.main.MainActivity;
import com.aiculabs.melchord.ui.release.ReleaseActivity;
import com.aiculabs.melchord.ui.search.SearchActivity;
import com.aiculabs.melchord.ui.searchResults.SearchResultsActivity;

/**
 * This component inject dependencies to all Activities across the application
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(SearchActivity searchActivity);
//    void inject(ArtistActivity artistActivity);
    void inject(SearchResultsActivity searchResultsActivity);
    void inject(ReleaseActivity releaseActivity);
}
