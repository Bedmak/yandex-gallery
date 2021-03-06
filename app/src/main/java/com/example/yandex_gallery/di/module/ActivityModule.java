package com.example.yandex_gallery.di.module;

import android.app.Activity;
import android.content.Context;
import android.view.WindowManager;

import com.example.yandex_gallery.di.ActivityContext;
import com.example.yandex_gallery.di.scope.PerActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {
    private Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @ActivityContext
    @PerActivity
    @Provides
    Context provideActivityContext() {
        return activity;
    }

    @PerActivity
    @Provides
    WindowManager provideWindowManager() { return activity.getWindowManager(); }
}
