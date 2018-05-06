package com.example.yandex_gallery.ui.gallery;

import com.example.yandex_gallery.data.models.ImagesResponse;
import com.example.yandex_gallery.data.repository.ApiRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import io.reactivex.Single;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GalleryPresenterTest {

    private static final int ANY_OFFSET = 1;
    private static final int ANY_LIMIT = 10;

    @Mock
    private ApiRepository apiRepository;

    @Mock
    private GalleryContract.GalleryView view;

    @InjectMocks
    private GalleryPresenterImpl presenter;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        presenter.onAttach(view);
    }

    @Test
    public void loadGalleryFromDB() {
        ImagesResponse dbResponse = new ImagesResponse();
        Single<ImagesResponse> dbEmitter = Single.just(dbResponse);
        when(apiRepository.requestImagesDataFromDB(ANY_OFFSET))
                .thenReturn(dbEmitter);
        when(apiRepository.requestImagesData(ANY_LIMIT, ANY_OFFSET))
                .thenReturn(Single.just(new ImagesResponse()));

        presenter.requestImages(ANY_OFFSET);

        InOrder inOrder = Mockito.inOrder(view);
        //request started
        inOrder.verify(view).showLoading();
        inOrder.verify(view).hideErrorView();
        //result received
        inOrder.verify(view).hideLoading();
        inOrder.verify(view).hideErrorView();
        inOrder.verify(view).showImages(dbResponse);
    }

    @Test
    public void loadGalleryFromNetwork() {
        ImagesResponse networkResponse = new ImagesResponse();
        Single<ImagesResponse> networkEmitter = Single.just(networkResponse);
        when(apiRepository.requestImagesData(ANY_LIMIT, ANY_OFFSET))
                .thenReturn(networkEmitter);
        when(apiRepository.requestImagesDataFromDB(ANY_OFFSET))
                .thenReturn(Single.error(new RuntimeException("DB error")));

        presenter.requestImages(ANY_OFFSET);

        verify(view).hideLoading();
        verify(view, times(2)).hideErrorView();
        verify(view).showImages(networkResponse);
    }

    @Test
    public void loadGalleryFailure() {
        Throwable networkException = new Throwable("Network error");
        when(apiRepository.requestImagesData(ANY_LIMIT, ANY_OFFSET))
                .thenReturn(Single.error(networkException));
        when(apiRepository.requestImagesDataFromDB(ANY_OFFSET))
                .thenReturn(Single.error(new RuntimeException("DB error")));

        presenter.requestImages(ANY_OFFSET);

        verify(view).hideLoading();
        verify(view).showErrorView(networkException);
    }

    @Test
    public void loadMoreImagesFromDB() {
        ImagesResponse dbResponse = new ImagesResponse();
        Single<ImagesResponse> networkEmitter = Single.just(dbResponse);
        when(apiRepository.requestImagesDataFromDB(ANY_OFFSET))
                .thenReturn(networkEmitter);
        when(apiRepository.requestImagesData(ANY_LIMIT, ANY_OFFSET))
                .thenReturn(Single.error(new RuntimeException("Network error")));

        presenter.requestMoreImages(ANY_OFFSET);

        verify(view).showMoreImages(dbResponse);
    }

    @Test
    public void loadMoreImagesFromNetwork() {
        ImagesResponse networkResponse = new ImagesResponse();
        Single<ImagesResponse> networkEmitter = Single.just(networkResponse);
        when(apiRepository.requestImagesData(ANY_LIMIT, ANY_OFFSET))
                .thenReturn(networkEmitter);
        when(apiRepository.requestImagesDataFromDB(ANY_OFFSET))
                .thenReturn(Single.error(new RuntimeException("DB error")));

        presenter.requestMoreImages(ANY_OFFSET);

        verify(view).showMoreImages(networkResponse);
    }

    @Test
    public void loadMoreImagesFailure() {
        Throwable networkException = new Throwable("Network error");
        when(apiRepository.requestImagesData(ANY_LIMIT, ANY_OFFSET))
                .thenReturn(Single.error(networkException));
        when(apiRepository.requestImagesDataFromDB(ANY_OFFSET))
                .thenReturn(Single.error(new RuntimeException("DB error")));

        presenter.requestMoreImages(ANY_OFFSET);

        verify(view, never()).showMoreImages(any(ImagesResponse.class));
    }
}
