package com.example.yandex_gallery.di.component;

import com.example.yandex_gallery.di.module.FragmentModule;
import com.example.yandex_gallery.di.scope.PerFragment;

import dagger.Component;

@PerFragment
@Component(dependencies = ActivityComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {

}
