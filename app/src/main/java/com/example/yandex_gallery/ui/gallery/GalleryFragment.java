package com.example.yandex_gallery.ui.gallery;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.yandex_gallery.R;
import com.example.yandex_gallery.data.models.ImagesResponse;
import com.example.yandex_gallery.ui.base.BaseFragment;
import com.example.yandex_gallery.ui.base.MvpPresenter;
import com.example.yandex_gallery.ui.detailed.DetailedActivity;
import com.example.yandex_gallery.ui.gallery.adapter.GalleryAdapter;
import com.example.yandex_gallery.utils.NetworkUtil;
import com.example.yandex_gallery.utils.listener.PaginationScrollListener;


import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import timber.log.Timber;

import static com.example.yandex_gallery.utils.AppConstants.IMAGE_TRANSITION;
import static com.example.yandex_gallery.utils.AppConstants.IMAGE_URL;

public class GalleryFragment extends BaseFragment implements GalleryContract.GalleryView, GalleryAdapter.OnImageClickListener {

    @BindView(R.id.galleryRecycler)
    RecyclerView galleryRecycler;

    @BindView(R.id.galleryProgress)
    ProgressBar progressBar;

    @BindView(R.id.error_layout)
    LinearLayout errorLayout;

    @BindView(R.id.errorText)
    TextView txtError;

    @BindView(R.id.errorTextCause)
    TextView txtErrorCause;


    @OnClick(R.id.errorBtnRetry)
    void onRetryClick() {
        galleryPresenter.requestImages(0);
    }

    @Inject
    GalleryAdapter galleryAdapter;

    @Inject
    GalleryPresenterImpl galleryPresenter;

    @Inject
    NetworkUtil networkUtil;

    private static final int PAGE_START = 0;
    private int totalPages = 1;
    private boolean isLoading = false;
    private boolean isLastPage = false;
    private int currentPage = PAGE_START;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRecyclerView();
        galleryPresenter.requestImages(0);
    }

    private void initRecyclerView() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        galleryRecycler.setAdapter(galleryAdapter);
        galleryRecycler.setLayoutManager(gridLayoutManager);
        galleryRecycler.setItemAnimator(new DefaultItemAnimator());
        galleryAdapter.setOnImageClickListener(this);

        galleryRecycler.addOnScrollListener(new PaginationScrollListener(gridLayoutManager) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                currentPage += 1;
                galleryPresenter.requestMoreImages(currentPage * 10);
            }

            @Override
            public int getTotalPageCount() {
                return totalPages;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }
        });
    }

    @Override
    public void showImages(ImagesResponse response) {
        galleryAdapter.setData(response.getEmbedded().getItems());
        if (response.getEmbedded().getTotal() % 10 != 0) {
            totalPages = (response.getEmbedded().getTotal() / 10) + 1;
        } else {
            totalPages = response.getEmbedded().getTotal() / 10;
        }
        Timber.d("Total pages - %s", totalPages);

        if (currentPage <= totalPages) galleryAdapter.addLoadingFooter();
        else isLastPage = true;
    }

    @Override
    public void showMoreImages(ImagesResponse response) {
        galleryAdapter.removeLoadingFooter();
        galleryAdapter.addAll(response.getEmbedded().getItems());
        isLoading = false;

        if (currentPage < totalPages) galleryAdapter.addLoadingFooter();
        else isLastPage = true;
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

            txtError.setText(getResources().getString(R.string.error_msg_gallery));
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

    @Override
    public void onImageClick(String url, ImageView sharedImageView) {
        Intent intent = new Intent(getActivity(), DetailedActivity.class);
        intent.putExtra(IMAGE_URL, url);
        intent.putExtra(IMAGE_TRANSITION, ViewCompat.getTransitionName(sharedImageView));
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                getActivity(),
                sharedImageView,
                ViewCompat.getTransitionName(sharedImageView)
        );
        startActivity(intent, options.toBundle());
    }
}
