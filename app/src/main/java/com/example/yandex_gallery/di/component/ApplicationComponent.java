package com.example.yandex_gallery.di.component;

import com.example.yandex_gallery.App;
import com.example.yandex_gallery.di.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(App app);
}
