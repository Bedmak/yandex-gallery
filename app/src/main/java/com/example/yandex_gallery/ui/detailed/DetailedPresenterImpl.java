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
                apiRepository.requestImageFromDB((offset - (offset % 10)), offset)  // (offset - (offset % 10) - this expression indicates which entry contains a link to the image
                .onErrorResumeNext(apiRepository.requestImage(offset))
                        .subscribe(
                                item -> {
                                    getView().hideLoading();
                                    getView().hideErrorView();
                                    getView().showImage(item.getFile());
                                },
                                err -> {
                                    getView().hideLoading();
                                    getView().showErrorView(err);
                                    Timber.e(err);
                                })
        );
    }

}
