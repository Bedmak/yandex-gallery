package com.example.yandex_gallery.ui.gallery;


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

    @Override
    public void requestImages(int offset) {
        getView().showLoading();
        getView().hideErrorView();
        getCompositeDisposable().add(
                apiRepository.requestImagesData(10, offset)
                        .subscribe(
                                imagesResponse -> {
                                    getView().hideLoading();
                                    getView().hideErrorView();
                                    getView().showImages(imagesResponse);
                                    },
                                err -> {
                                    getView().hideLoading();
                                    getView().showErrorView(err);
                                    Timber.e(err);
                                })
        );
    }

    @Override
    public void requestMoreImages(int offset) {

        getCompositeDisposable().add(
                apiRepository.requestImagesData(10, offset)
                        .subscribe(
                                imagesResponse -> getView().showMoreImages(imagesResponse),
                                err -> {
                                    Timber.d(err);
                                })
        );
    }

}
