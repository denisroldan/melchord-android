package com.aiculabs.melchord.data;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import rx.functions.Action0;
import rx.functions.Func1;
import com.aiculabs.melchord.data.local.DatabaseHelper;
import com.aiculabs.melchord.data.local.PreferencesHelper;
import com.aiculabs.melchord.data.model.ArtistSearch;
import com.aiculabs.melchord.data.model.Ribot;
import com.aiculabs.melchord.data.remote.ArtistService;
import com.aiculabs.melchord.data.remote.RibotsService;
import com.aiculabs.melchord.util.EventPosterHelper;

@Singleton
public class DataManager {

    /*
    *
    * The DataManager is the brain of the architecture.
    * It extensively uses RxJava operators to combine, filter and transform data retrieved from helper classes.
    * The aim of the DataManager is to reduce the amount of work that Activities and Fragments
    * have to do by providing data that is ready to display and won’t usually need any transformation.
    *
    * */


    private final RibotsService mRibotsService;
    private final ArtistService mArtistSearchService;
    private final DatabaseHelper mDatabaseHelper;
    private final PreferencesHelper mPreferencesHelper;
    private final EventPosterHelper mEventPoster;


    @Inject
    public DataManager(RibotsService ribotsService, ArtistService artistSearchService, PreferencesHelper preferencesHelper,
                       DatabaseHelper databaseHelper, EventPosterHelper eventPosterHelper) {
        mRibotsService = ribotsService;
        mArtistSearchService = artistSearchService;
        mPreferencesHelper = preferencesHelper;
        mDatabaseHelper = databaseHelper;
        mEventPoster = eventPosterHelper;
    }


    public PreferencesHelper getPreferencesHelper() {
        return mPreferencesHelper;
    }

    public Observable<Ribot> syncRibots() {
        return mRibotsService.getRibots()
                .concatMap(new Func1<List<Ribot>, Observable<Ribot>>() {
                    @Override
                    public Observable<Ribot> call(List<Ribot> ribots) {
                        return mDatabaseHelper.setRibots(ribots);
                    }
                });
    }

    public Observable<List<Ribot>> getRibots() {
        return mDatabaseHelper.getRibots().distinct();
    }

    // TODO - Esto funcionará?? xDD
    public Observable<List<ArtistSearch>> getSearchResults(final String query){
        return mArtistSearchService.getArtistSearchResults(query)
                .concatMap(new Func1<List<ArtistSearch>, Observable<? extends List<ArtistSearch>>>() {
                    @Override
                    public Observable<? extends List<ArtistSearch>> call(List<ArtistSearch> artistSearches) {
                        return mArtistSearchService.getArtistSearchResults(query);
                    }
                });
    }

    /// Helper method to post events from doOnCompleted.
    private Action0 postEventAction(final Object event) {
        return new Action0() {
            @Override
            public void call() {
                mEventPoster.postEventSafely(event);
            }
        };
    }

}
