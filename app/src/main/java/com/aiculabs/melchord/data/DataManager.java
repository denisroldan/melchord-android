package com.aiculabs.melchord.data;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import rx.functions.Func1;
import com.aiculabs.melchord.data.local.DatabaseHelper;
import com.aiculabs.melchord.data.local.PreferencesHelper;
import com.aiculabs.melchord.data.model.Artist;
import com.aiculabs.melchord.data.model.ArtistSearch;
import com.aiculabs.melchord.data.model.Release;
import com.aiculabs.melchord.data.model.Song;
import com.aiculabs.melchord.data.model.Tab;
import com.aiculabs.melchord.data.remote.ArtistService;
import com.aiculabs.melchord.data.remote.ReleaseService;
import com.aiculabs.melchord.data.remote.SongService;
import com.aiculabs.melchord.data.remote.TabService;

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
    private final TabService mTabService;
    private final DatabaseHelper mDatabaseHelper;
    private final PreferencesHelper mPreferencesHelper;

    @Inject
    public DataManager(ArtistService artistSearchService, ReleaseService releaseService, SongService songService, TabService tabService, PreferencesHelper preferencesHelper,
                       DatabaseHelper databaseHelper) {
        mArtistService = artistSearchService;
        mReleaseService = releaseService;
        mSongService = songService;
        mTabService = tabService;
        mPreferencesHelper = preferencesHelper;
        mDatabaseHelper = databaseHelper;
    }


    public PreferencesHelper getPreferencesHelper() {
        return mPreferencesHelper;
    }

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
    
    public Observable<Song> getSong(final String mbid) {
        return mSongService.getSong(mbid);
    }

    public Observable<Tab> getTab(final Integer id) {
        return mTabService.getTab(id);
    }

}
