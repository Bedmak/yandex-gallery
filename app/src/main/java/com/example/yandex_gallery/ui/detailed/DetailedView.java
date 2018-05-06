package com.example.yandex_gallery.ui.detailed;

import com.example.yandex_gallery.ui.base.MvpView;

public interface DetailedView extends MvpView{

    void showImage(String url);
    void showLoading();
    void hideLoading();
    void showErrorView(Throwable throwable);
    void hideErrorView();

}
