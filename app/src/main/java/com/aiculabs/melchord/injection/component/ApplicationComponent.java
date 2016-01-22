package com.aiculabs.melchord.injection.component;

import android.app.Application;
import android.content.Context;

import com.aiculabs.melchord.data.remote.ArtistService;
import com.aiculabs.melchord.data.remote.ReleaseService;
import com.aiculabs.melchord.data.remote.SongService;
import com.squareup.otto.Bus;

import javax.inject.Singleton;

import dagger.Component;
import com.aiculabs.melchord.data.DataManager;
import com.aiculabs.melchord.data.SyncService;
import com.aiculabs.melchord.data.local.DatabaseHelper;
import com.aiculabs.melchord.data.local.PreferencesHelper;
import com.aiculabs.melchord.data.remote.RibotsService;
import com.aiculabs.melchord.injection.ApplicationContext;
import com.aiculabs.melchord.injection.module.ApplicationModule;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(SyncService syncService);

    @ApplicationContext Context context();
    Application application();
    RibotsService ribotsService();
    ArtistService artistSearchService();
    ReleaseService releaseService();
    SongService songService();
    PreferencesHelper preferencesHelper();
    DatabaseHelper databaseHelper();
    DataManager dataManager();
    Bus eventBus();

}
