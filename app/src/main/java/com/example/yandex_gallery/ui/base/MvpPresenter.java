package com.example.yandex_gallery.ui.base;

public interface MvpPresenter<V extends MvpView> {
    void onAttach(V view);
    void onDetach();
}
