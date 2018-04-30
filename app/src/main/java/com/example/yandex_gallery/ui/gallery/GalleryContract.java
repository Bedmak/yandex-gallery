package com.example.yandex_gallery.ui.gallery;

import com.example.yandex_gallery.data.models.ImagesResponse;
import com.example.yandex_gallery.ui.base.MvpPresenter;
import com.example.yandex_gallery.ui.base.MvpView;


public interface GalleryContract {

    interface GalleryView extends MvpView {
        void showImages(ImagesResponse response);
        void showMoreImages(ImagesResponse response);
        void showLoading();
        void hideLoading();
        void showErrorView(Throwable throwable);
        void hideErrorView();
    }

    interface GalleryPresenter extends MvpPresenter<GalleryView> {
        void requestImages(int offset);
        void requestMoreImages(int offset);
    }

}
