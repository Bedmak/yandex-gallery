package com.example.yandex_gallery.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.yandex_gallery.R;
import com.example.yandex_gallery.di.ApplicationContext;

import java.util.concurrent.TimeoutException;

import javax.inject.Inject;

public class NetworkUtil {

    private final Context context;

    @Inject
    NetworkUtil(@ApplicationContext Context context) {
        this.context = context;
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    public String fetchErrorMessage(Throwable throwable) {
        String errorMsg = context.getResources().getString(R.string.error_msg_unknown);

        if (!isNetworkConnected()) {
            errorMsg = context.getResources().getString(R.string.error_msg_no_internet);
        } else if (throwable instanceof TimeoutException) {
            errorMsg = context.getResources().getString(R.string.error_msg_timeout);
        }

        return errorMsg;
    }
}
