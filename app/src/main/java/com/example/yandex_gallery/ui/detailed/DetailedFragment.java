package com.example.yandex_gallery.ui.detailed;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.yandex_gallery.R;
import com.example.yandex_gallery.ui.base.BaseFragment;
import com.example.yandex_gallery.ui.base.MvpPresenter;
import com.example.yandex_gallery.utils.NetworkUtil;
import com.github.chrisbanes.photoview.PhotoView;

import javax.inject.Inject;

import butterknife.BindView;

import static com.example.yandex_gallery.utils.AppConstants.IMAGE_ID;


public class DetailedFragment extends BaseFragment implements DetailedContract.DetailedView {

    @BindView(R.id.detailedImage)
    PhotoView imageView;

    @BindView(R.id.detailedProgress)
    ProgressBar progressBar;

    @BindView(R.id.error_layout)
    LinearLayout errorLayout;

    @BindView(R.id.errorText)
    TextView txtError;

    @BindView(R.id.errorTextCause)
    TextView txtErrorCause;

    @Inject
    DetailedPresenterImpl presenter;

    @Inject
    NetworkUtil networkUtil;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.requestDetailedImage(getActivity().getIntent().getIntExtra(IMAGE_ID, 0));
    }

    @Override
    public void showImage(String url) {
        Glide
                .with(this)
                .load(url)
                .into(imageView);
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showErrorView(Throwable throwable) {
        if (errorLayout.getVisibility() == View.GONE) {
            errorLayout.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);

            txtError.setText(getResources().getString(R.string.error_msg_detailed));
            txtErrorCause.setText(networkUtil.fetchErrorMessage(throwable));
        }
    }

    @Override
    public void hideErrorView() {
        if (errorLayout.getVisibility() == View.VISIBLE) {
            errorLayout.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
        }
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
