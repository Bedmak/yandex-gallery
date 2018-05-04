package com.example.yandex_gallery;

import android.app.Application;

import com.example.yandex_gallery.di.component.ApplicationComponent;
import com.example.yandex_gallery.di.component.DaggerApplicationComponent;
import com.example.yandex_gallery.di.module.ApplicationModule;

import io.paperdb.Paper;
import timber.log.Timber;


public class App extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = initDaggerApplicationComponent();
        applicationComponent.inject(this);
        Timber.plant(new Timber.DebugTree());
        Paper.init(this);
    }


    public ApplicationComponent initDaggerApplicationComponent() {
        return DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
