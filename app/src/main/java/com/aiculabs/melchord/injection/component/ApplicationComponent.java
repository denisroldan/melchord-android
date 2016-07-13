package com.aiculabs.melchord.injection.component;

import android.app.Application;
import android.content.Context;

import com.aiculabs.melchord.data.DataManager;
import com.aiculabs.melchord.data.SyncService;
import com.aiculabs.melchord.data.local.DatabaseHelper;
import com.aiculabs.melchord.data.local.PreferencesHelper;
import com.aiculabs.melchord.data.remote.ArtistService;
import com.aiculabs.melchord.data.remote.ReleaseService;
import com.aiculabs.melchord.data.remote.SongService;
import com.aiculabs.melchord.data.remote.TabService;
import com.aiculabs.melchord.injection.ApplicationContext;
import com.aiculabs.melchord.injection.module.ApplicationModule;
import com.aiculabs.melchord.util.RxEventBus;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(SyncService syncService);

    @ApplicationContext Context context();
    Application application();
    ArtistService artistSearchService();
    ReleaseService releaseService();
    SongService songService();
    TabService tabService();
    PreferencesHelper preferencesHelper();
    DatabaseHelper databaseHelper();
    DataManager dataManager();
    RxEventBus eventBus();
}
