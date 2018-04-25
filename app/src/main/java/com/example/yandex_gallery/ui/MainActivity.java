package com.example.yandex_gallery.ui;

import android.os.Bundle;

import com.example.yandex_gallery.R;
import com.example.yandex_gallery.ui.base.BaseActivity;
import com.example.yandex_gallery.ui.base.MvpPresenter;
import com.example.yandex_gallery.ui.main.MainPresenter;

import javax.inject.Inject;

public class MainActivity extends BaseActivity {

    @Inject
    MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int provideLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected MvpPresenter providePresenter() {
        return mainPresenter;
    }

    public void inject() {
        getActivityComponent().inject(this);
    }
}
