package com.aiculabs.melchord.injection.module;

import android.app.Application;
import android.content.Context;

import com.aiculabs.melchord.data.remote.ArtistService;
import com.aiculabs.melchord.data.remote.ReleaseService;
import com.aiculabs.melchord.data.remote.SongService;
import com.squareup.otto.Bus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import com.aiculabs.melchord.data.remote.RibotsService;
import com.aiculabs.melchord.injection.ApplicationContext;

/**
 * Provide application-level dependencies.
 */
@Module
public class ApplicationModule {
    protected final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    @Singleton
    Bus provideEventBus() {
        return new Bus();
    }

    @Provides
    @Singleton
    RibotsService provideRibotsService() {
        return RibotsService.Creator.newRibotsService();
    }

    @Provides
    @Singleton
    ArtistService provideArtistService() {
        return ArtistService.Creator.newArtistsService();
    }

    @Provides
    @Singleton
    ReleaseService provideReleaseService() {
        return ReleaseService.Creator.newReleaseService();
    }

    @Provides
    @Singleton
    SongService provideSongService() {
        return SongService.Creator.newSongService();
    }
}
