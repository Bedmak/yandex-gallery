package com.example.yandex_gallery.ui.detailed;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.yandex_gallery.R;
import com.example.yandex_gallery.data.models.Item;
import com.example.yandex_gallery.ui.base.BaseFragment;
import com.example.yandex_gallery.ui.base.MvpPresenter;

import javax.inject.Inject;

import butterknife.BindView;

import static com.example.yandex_gallery.utils.AppConstants.IMAGE_ID;


public class DetailedFragment extends BaseFragment implements DetailedContract.DetailedView {

    @BindView(R.id.detailedImage)
    ImageView imageView;

    @Inject
    DetailedPresenterImpl presenter;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.requestDetailedImage(getActivity().getIntent().getIntExtra(IMAGE_ID, 0));
    }

    @Override
    public void showImage(Item image) {
        //progressBar.setVisibility(View.GONE);
        Glide
                .with(this)
                .load(image.getFile())
                .into(imageView);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showErrorView(Throwable throwable) {

    }

    @Override
    public void hideErrorView() {

    }

    @Override
    protected int provideLayout() {
        return R.layout.fragment_detailed;
    }

    @Override
    protected MvpPresenter providePresenter() {
        return presenter;
    }

    @Override
    protected void inject() {
        getFragmentComponent().inject(this);
    }
}
