/*
 * Chromer
 * Copyright (C) 2017 Arunkumar
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package arun.com.chromer.di.data;

import android.app.Application;

import javax.inject.Singleton;

import arun.com.chromer.data.apps.AppDiskStore;
import arun.com.chromer.data.apps.AppRepository;
import arun.com.chromer.data.apps.DefaultAppRepository;
import arun.com.chromer.data.apps.store.AppStore;
import arun.com.chromer.data.history.DefaultHistoryRepository;
import arun.com.chromer.data.history.HistoryRepository;
import arun.com.chromer.data.history.HistorySqlDiskStore;
import arun.com.chromer.data.history.HistoryStore;
import arun.com.chromer.data.qualifiers.Disk;
import arun.com.chromer.data.qualifiers.Network;
import arun.com.chromer.data.website.DefaultWebsiteRepository;
import arun.com.chromer.data.website.WebsiteDiskStore;
import arun.com.chromer.data.website.WebsiteNetworkStore;
import arun.com.chromer.data.website.WebsiteRepository;
import arun.com.chromer.data.website.WebsiteStore;
import arun.com.chromer.settings.Preferences;
import dagger.Module;
import dagger.Provides;

/**
 * Created by arunk on 15-12-2017.
 */
@Module
public class TestDataModule {

    private final Application appplication;

    public TestDataModule(Application application) {
        this.appplication = application;
    }

    @Provides
    @Singleton
    Preferences providesPreferences() {
        return Preferences.get(appplication);
    }

    @Provides
    @Singleton
    AppStore appStore(AppDiskStore appDiskStore) {
        return appDiskStore;
    }

    @Provides
    @Singleton
    AppRepository appRepository(DefaultAppRepository appRepository) {
        return appRepository;
    }

    @Provides
    @Singleton
    HistoryStore historyStore(HistorySqlDiskStore historySqlDiskStore) {
        return historySqlDiskStore;
    }

    @Provides
    @Singleton
    HistoryRepository historyRepository(DefaultHistoryRepository historyRepository) {
        return historyRepository;
    }

    @Provides
    @Singleton
    @Disk
    WebsiteStore websiteDiskStore(WebsiteDiskStore websiteDiskStore) {
        return websiteDiskStore;
    }

    @Provides
    @Singleton
    @Network
    WebsiteStore websiteNetworkStore(WebsiteNetworkStore websiteNetworkStore) {
        return websiteNetworkStore;
    }

    @Provides
    @Singleton
    WebsiteRepository websiteRepository(DefaultWebsiteRepository websiteRepository) {
        return websiteRepository;
    }
}