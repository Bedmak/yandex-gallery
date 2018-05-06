package com.example.yandex_gallery.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yandex_gallery.di.component.DaggerFragmentComponent;
import com.example.yandex_gallery.di.component.FragmentComponent;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment implements MvpView {

    private Unbinder unbinder;
    private ComponentActivity componentActivity;
    private FragmentComponent fragmentComponent;
    private MvpPresenter presenter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ComponentActivity) {
            componentActivity = (ComponentActivity) context;
        } else {
            throw new IllegalStateException("Host activity must implement ComponentActivity interface.");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        componentActivity = null;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentComponent = initDaggerFragmentComponent();
        inject();
        View view = inflater.inflate(provideLayout(), container, false);
        unbinder = ButterKnife.bind(this, view);
        presenter = providePresenter();
        if (presenter != null)
            presenter.onAttach(this);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        if (presenter != null)
            presenter.onDetach();
    }

    public FragmentComponent initDaggerFragmentComponent() {
        return DaggerFragmentComponent.builder()
                .activityComponent(componentActivity.getActivityComponent())
                .build();
    }

    protected abstract int provideLayout();

    protected MvpPresenter providePresenter() {
        return null;
    }

    protected final FragmentComponent getFragmentComponent() {
        return fragmentComponent;
    }

    protected void inject() {}
}
