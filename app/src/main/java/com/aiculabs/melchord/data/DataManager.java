package com.aiculabs.melchord.data;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import rx.functions.Action0;
import rx.functions.Func1;
import com.aiculabs.melchord.data.local.DatabaseHelper;
import com.aiculabs.melchord.data.local.PreferencesHelper;
import com.aiculabs.melchord.data.model.Artist;
import com.aiculabs.melchord.data.model.ArtistSearch;
import com.aiculabs.melchord.data.model.Release;
import com.aiculabs.melchord.data.model.Song;
import com.aiculabs.melchord.data.remote.ArtistService;
import com.aiculabs.melchord.data.remote.ReleaseService;
import com.aiculabs.melchord.data.remote.SongService;
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


    private final ArtistService mArtistService;
    private final ReleaseService mReleaseService;
    private final SongService mSongService;
    private final DatabaseHelper mDatabaseHelper;
    private final PreferencesHelper mPreferencesHelper;
    private final EventPosterHelper mEventPoster;


    @Inject
    public DataManager(ArtistService artistSearchService, ReleaseService releaseService, SongService songService, PreferencesHelper preferencesHelper,
                       DatabaseHelper databaseHelper, EventPosterHelper eventPosterHelper) {
        mArtistService = artistSearchService;
        mReleaseService = releaseService;
        mSongService = songService;
        mPreferencesHelper = preferencesHelper;
        mDatabaseHelper = databaseHelper;
        mEventPoster = eventPosterHelper;
    }


    public PreferencesHelper getPreferencesHelper() {
        return mPreferencesHelper;
    }

    // TODO - Esto funcionará?? xDD
    public Observable<List<ArtistSearch>> getSearchResults(final String query){
        return mArtistService.getArtistSearchResults(query)
                .concatMap(new Func1<List<ArtistSearch>, Observable<? extends List<ArtistSearch>>>() {
                    @Override
                    public Observable<? extends List<ArtistSearch>> call(List<ArtistSearch> artistSearches) {
                        return mArtistService.getArtistSearchResults(query);
                    }
                });
    }

    public Observable<Release> getReleaseResults(final String mbid) {
        return mReleaseService.getRelease(mbid);
    }
    
    public Observable<Artist> getArtist(final String mbid){
        return mArtistService.getArtist(mbid);
    }
    
    public Observable<Song> getSong(final String mbid){
        return mSongService.getSong(mbid);
        
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
