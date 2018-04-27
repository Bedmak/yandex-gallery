package com.example.yandex_gallery.di.component;

import android.content.Context;

import com.example.yandex_gallery.data.network.Api;
import com.example.yandex_gallery.di.ActivityContext;
import com.example.yandex_gallery.di.module.ActivityModule;
import com.example.yandex_gallery.di.scope.PerActivity;
import com.example.yandex_gallery.ui.main.MainActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(MainActivity mainActivity);

    @ActivityContext
    Context context();

    Api api();
}
