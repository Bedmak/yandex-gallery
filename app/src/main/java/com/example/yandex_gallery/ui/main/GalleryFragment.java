package com.example.yandex_gallery.ui.main;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.yandex_gallery.R;
import com.example.yandex_gallery.data.models.Item;
import com.example.yandex_gallery.ui.base.BaseFragment;
import com.example.yandex_gallery.ui.base.MvpPresenter;
import com.example.yandex_gallery.ui.main.adapter.GalleryAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class GalleryFragment extends BaseFragment implements GalleryContract.GalleryView {

    @BindView(R.id.galleryRecycler)
    RecyclerView galleryRecycler;

    @Inject
    GalleryAdapter galleryAdapter;

    @Inject
    GalleryPresenterImpl galleryPresenter;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRecyclerView();
        galleryPresenter.requestImagesInfo(0);
    }

    private void initRecyclerView() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        galleryRecycler.setAdapter(galleryAdapter);
        galleryRecycler.setLayoutManager(gridLayoutManager);
        galleryRecycler.setItemAnimator(new DefaultItemAnimator());

        //galleryRecycler.addOnScrollListener();
    }

    @Override
    public void showImages(List<Item> items) {
        galleryAdapter.setData(items);
    }

    @Override
    protected int provideLayout() {
        return R.layout.fragment_gallery;
    }

    @Override
    protected MvpPresenter providePresenter() {
        return galleryPresenter;
    }

    @Override
    protected void inject() {
        getFragmentComponent().inject(this);
    }
}
