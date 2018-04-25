package com.example.yandex_gallery.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.yandex_gallery.App;
import com.example.yandex_gallery.di.component.ActivityComponent;
import com.example.yandex_gallery.di.component.DaggerActivityComponent;
import com.example.yandex_gallery.di.module.ActivityModule;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity implements ComponentActivity, MvpView {

    private ActivityComponent activityComponent;
    private Unbinder unbinder;
    private MvpPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent = initDaggerActivityComponent();
        inject();
        setContentView(provideLayout());
        unbinder = ButterKnife.bind(this);
        presenter = providePresenter();
        presenter.onAttach(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDetach();
        unbinder.unbind();
    }

    public ActivityComponent initDaggerActivityComponent() {
        return DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .applicationComponent(((App) getApplication()).getApplicationComponent())
                .build();
    }

    @Override
    public ActivityComponent getActivityComponent() {
        return activityComponent;
    }

    protected abstract int provideLayout();

    protected abstract MvpPresenter providePresenter();

    public void inject() {}
}
