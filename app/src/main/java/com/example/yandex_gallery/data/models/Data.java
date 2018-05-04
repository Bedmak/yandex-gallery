package com.example.yandex_gallery.data.models;

public class Data {

    private static final long STALE_MS = 600 * 1000; // Data is stale after 10 minutes

    private final ImagesResponse response;

    private final long timestamp;

    public Data(ImagesResponse response) {
        this.response = response;
        this.timestamp = System.currentTimeMillis();
    }

    public boolean isUpToDate() {
        return System.currentTimeMillis() - timestamp < STALE_MS;
    }

    public ImagesResponse getResponse() {
        return response;
    }
}
