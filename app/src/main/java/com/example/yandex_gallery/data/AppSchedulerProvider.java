package com.example.yandex_gallery.data;

import javax.inject.Inject;

import io.reactivex.SingleTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class AppSchedulerProvider implements SchedulerProvider {

    @Inject
    public AppSchedulerProvider() {}

    @Override
    public <T> SingleTransformer<T, T> applyIoSchedulers() {
        return single -> single.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
