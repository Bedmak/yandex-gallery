package com.example.yandex_gallery.di.module;

import com.example.yandex_gallery.data.AppSchedulerProvider;
import com.example.yandex_gallery.data.SchedulerProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class SchedulerModule {

    @Singleton
    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }

}
