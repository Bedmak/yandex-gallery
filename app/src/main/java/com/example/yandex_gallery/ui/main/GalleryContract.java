package com.example.yandex_gallery.ui.main;

import com.example.yandex_gallery.data.models.Item;
import com.example.yandex_gallery.ui.base.MvpPresenter;
import com.example.yandex_gallery.ui.base.MvpView;

import java.util.List;

public interface GalleryContract {

    interface GalleryView extends MvpView {
        void showImages(List<Item> items);
    }

    interface GalleryPresenter extends MvpPresenter<GalleryView> {
        void requestImagesInfo(int offset);
    }

}
