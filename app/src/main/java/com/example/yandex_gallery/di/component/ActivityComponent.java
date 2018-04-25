package com.example.yandex_gallery.di.component;

import com.example.yandex_gallery.di.module.ActivityModule;
import com.example.yandex_gallery.di.scope.PerActivity;
import com.example.yandex_gallery.ui.MainActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(MainActivity mainActivity);
}
