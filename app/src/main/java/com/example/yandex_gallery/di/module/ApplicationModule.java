package com.example.yandex_gallery.di.module;

import android.app.Application;
import android.content.Context;

import com.example.yandex_gallery.di.ApplicationContext;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private final Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @ApplicationContext
    @Singleton
    @Provides
    Context provideApplicationContext() {
        return application;
    }

}
