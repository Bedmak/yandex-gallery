package com.example.yandex_gallery.di.component;

import com.example.yandex_gallery.di.module.FragmentModule;
import com.example.yandex_gallery.di.scope.PerFragment;
import com.example.yandex_gallery.ui.detailed.DetailedFragment;
import com.example.yandex_gallery.ui.gallery.GalleryFragment;

import dagger.Component;

@PerFragment
@Component(dependencies = ActivityComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {
    void inject(GalleryFragment galleryFragment);
    void inject(DetailedFragment detailedFragment);
}
