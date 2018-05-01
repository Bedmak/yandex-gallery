package com.example.yandex_gallery.ui.detailed;

import com.example.yandex_gallery.data.models.Item;
import com.example.yandex_gallery.ui.base.MvpView;
import com.example.yandex_gallery.ui.base.MvpPresenter;

public interface DetailedContract {

    interface DetailedView extends MvpView {
        void showImage(Item image);
        void showLoading();
        void hideLoading();
        void showErrorView(Throwable throwable);
        void hideErrorView();
    }

    interface DetailedPresenter extends MvpPresenter<DetailedView> {
        void requestDetailedImage(int offset);
    }

}
