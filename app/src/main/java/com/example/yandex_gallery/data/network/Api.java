package com.example.yandex_gallery.data.network;

import com.example.yandex_gallery.data.models.ImagesResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    @GET("resources")
    Single<ImagesResponse> getListImages(@Query("public_key") String publicKey, @Query("limit") int limit, @Query("offset") int offset);

}
