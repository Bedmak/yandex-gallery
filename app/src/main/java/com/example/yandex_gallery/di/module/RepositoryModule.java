package com.example.yandex_gallery.di.module;

import com.example.yandex_gallery.data.SchedulerProvider;
import com.example.yandex_gallery.data.network.Api;
import com.example.yandex_gallery.data.repository.ApiRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

    @Singleton
    @Provides
    ApiRepository provideApiRepository(Api api, SchedulerProvider schedulerProvider) {
        return new ApiRepository(api, schedulerProvider);
    }

}
