package com.example.yandex_gallery.di.component;

import com.example.yandex_gallery.App;
import com.example.yandex_gallery.data.network.Api;
import com.example.yandex_gallery.data.network.NetworkModule;
import com.example.yandex_gallery.di.module.ApplicationModule;
import com.example.yandex_gallery.utils.NetworkUtil;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        ApplicationModule.class,
        NetworkModule.class
})
public interface ApplicationComponent {
    void inject(App app);

    Api api();
    NetworkUtil networkUtil();
}
