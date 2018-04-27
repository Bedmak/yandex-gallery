package com.example.yandex_gallery.data.repository;

import com.example.yandex_gallery.data.models.ImagesResponse;
import com.example.yandex_gallery.data.network.Api;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.example.yandex_gallery.utils.AppConstants.PUBLIC_KEY;

public class ApiRepository {

    private final Api api;

    @Inject
    ApiRepository(Api api) {
        this.api = api;
    }

    public Single<ImagesResponse> requestImagesData(int limit, int offset) {
        return api.getListImages(PUBLIC_KEY, limit, offset)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
