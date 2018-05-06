package com.example.yandex_gallery.di.component;

import com.example.yandex_gallery.App;
import com.example.yandex_gallery.data.SchedulerProvider;
import com.example.yandex_gallery.data.network.Api;
import com.example.yandex_gallery.data.network.NetworkModule;
import com.example.yandex_gallery.data.repository.ApiRepository;
import com.example.yandex_gallery.di.module.ApplicationModule;
import com.example.yandex_gallery.di.module.RepositoryModule;
import com.example.yandex_gallery.di.module.SchedulerModule;
import com.example.yandex_gallery.utils.NetworkUtil;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        ApplicationModule.class,
        NetworkModule.class,
        SchedulerModule.class,
        RepositoryModule.class
})
public interface ApplicationComponent {
    void inject(App app);

    ApiRepository apiRepository();
    NetworkUtil networkUtil();
}
