package com.example.yandex_gallery.data.repository;

import com.example.yandex_gallery.data.models.Data;
import com.example.yandex_gallery.data.models.ImagesResponse;
import com.example.yandex_gallery.data.models.Item;
import com.example.yandex_gallery.data.network.Api;

import javax.inject.Inject;

import io.paperdb.Paper;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.example.yandex_gallery.utils.AppConstants.IMAGE_RESPONSE;
import static com.example.yandex_gallery.utils.AppConstants.PUBLIC_KEY;

public class ApiRepository {

    private final Api api;

    @Inject
    ApiRepository(Api api) {
        this.api = api;
    }

    public Single<ImagesResponse> requestImagesData(int limit, int offset) {
        return api.getListImages(PUBLIC_KEY, limit, offset)
                .doOnSuccess(response -> Paper.book().write(IMAGE_RESPONSE + offset, new Data(response)))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<ImagesResponse> requestImagesDataFromDB(int offset) {
        return Single.fromCallable(() -> {
            Data data = Paper.book().read(IMAGE_RESPONSE + offset);
            if (data.isUpToDate()) {
                return data.getResponse();
            } else {
                Paper.book().delete(IMAGE_RESPONSE + offset);
            }
            throw new NullPointerException();
        });
    }

    public Single<Item> requestImage(int offset) {
        return api.getListImages(PUBLIC_KEY, 1, offset)
                .map(response -> response.getEmbedded().getItems().get(0))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<Item> requestImageFromDB(int offset, int id) {
        return Single.fromCallable(() -> {
            Data data = Paper.book().read(IMAGE_RESPONSE + offset);
            if (data.isUpToDate()) {
                return data.getResponse().getEmbedded().getItems().get(id);
            } else {
                Paper.book().delete(IMAGE_RESPONSE + offset);
            }
            throw new NullPointerException();
        });
    }

}
