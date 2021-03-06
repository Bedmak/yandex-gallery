package com.example.yandex_gallery.di.component;

import android.content.Context;
import android.view.WindowManager;

import com.example.yandex_gallery.data.repository.ApiRepository;
import com.example.yandex_gallery.di.ActivityContext;
import com.example.yandex_gallery.di.module.ActivityModule;
import com.example.yandex_gallery.di.scope.PerActivity;
import com.example.yandex_gallery.ui.MainActivity;
import com.example.yandex_gallery.ui.detailed.DetailedActivity;
import com.example.yandex_gallery.utils.NetworkUtil;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(MainActivity mainActivity);
    void inject(DetailedActivity detailedActivity);

    @ActivityContext
    Context context();
    WindowManager windowManager();

    ApiRepository apiRepository();
    NetworkUtil networkUtil();
}
