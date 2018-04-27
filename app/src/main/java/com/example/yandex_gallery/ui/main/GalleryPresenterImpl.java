package com.example.yandex_gallery.ui.main;

import android.annotation.SuppressLint;

import com.example.yandex_gallery.data.repository.ApiRepository;
import com.example.yandex_gallery.ui.base.BasePresenter;

import javax.inject.Inject;

import timber.log.Timber;

public class GalleryPresenterImpl extends BasePresenter<GalleryContract.GalleryView> implements GalleryContract.GalleryPresenter {

    private ApiRepository apiRepository;

    @Inject
    GalleryPresenterImpl(ApiRepository apiRepository) {
        this.apiRepository = apiRepository;
    }

    @SuppressLint("CheckResult")
    @Override
    public void requestImagesInfo(int offset) {
        apiRepository.requestImagesData(20, offset)
                .doOnSubscribe(disposable -> getCompositeDisposable().add(disposable))
                .subscribe(
                        imagesResponse -> getView().showImages(imagesResponse.getEmbedded().getItems()),
                        e -> {
                            Timber.e(e);
                        });
    }

}
