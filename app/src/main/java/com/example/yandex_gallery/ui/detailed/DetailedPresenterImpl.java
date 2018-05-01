package com.example.yandex_gallery.ui.detailed;

import com.example.yandex_gallery.data.repository.ApiRepository;
import com.example.yandex_gallery.ui.base.BasePresenter;

import javax.inject.Inject;

import timber.log.Timber;

public class DetailedPresenterImpl extends BasePresenter<DetailedContract.DetailedView> implements DetailedContract.DetailedPresenter {

    private ApiRepository apiRepository;

    @Inject
    DetailedPresenterImpl(ApiRepository apiRepository) {
        this.apiRepository = apiRepository;
    }

    @Override
    public void requestDetailedImage(int offset) {
        getView().showLoading();
        getView().hideErrorView();
        getCompositeDisposable().add(
                apiRepository.requestImagesData(1, offset)
                        .subscribe(
                                imagesResponse -> {
                                    getView().hideLoading();
                                    getView().hideErrorView();
                                    getView().showImage(imagesResponse.getEmbedded().getItems().get(0));
                                },
                                err -> {
                                    getView().hideLoading();
                                    getView().showErrorView(err);
                                    Timber.e(err);
                                })
        );
    }

}
