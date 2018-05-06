package com.example.yandex_gallery.data.repository;

import com.example.yandex_gallery.data.SchedulerProvider;
import com.example.yandex_gallery.data.models.Data;
import com.example.yandex_gallery.data.models.ImagesResponse;
import com.example.yandex_gallery.data.models.Item;
import com.example.yandex_gallery.data.network.Api;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.paperdb.Paper;
import io.reactivex.Single;

import static com.example.yandex_gallery.utils.AppConstants.IMAGE_RESPONSE;
import static com.example.yandex_gallery.utils.AppConstants.PUBLIC_KEY;

@Singleton
public class ApiRepository {

    private final Api api;
    private final SchedulerProvider schedulerProvider;

    @Inject
    public ApiRepository(Api api, SchedulerProvider schedulerProvider) {
        this.api = api;
        this.schedulerProvider = schedulerProvider;
    }

    public Single<ImagesResponse> requestImagesData(int limit, int offset) {
        return api.getListImages(PUBLIC_KEY, limit, offset)
                .doOnSuccess(response -> Paper.book().write(IMAGE_RESPONSE + offset, new Data(response)))
                .compose(schedulerProvider.applyIoSchedulers());
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
                .compose(schedulerProvider.applyIoSchedulers());
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
