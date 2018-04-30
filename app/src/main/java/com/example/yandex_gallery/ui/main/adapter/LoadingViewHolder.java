package com.example.yandex_gallery.ui.main.adapter;

import android.view.View;
import android.widget.ProgressBar;

import com.example.yandex_gallery.R;
import com.example.yandex_gallery.ui.base.BaseViewHolder;

import butterknife.BindView;


public class LoadingViewHolder extends BaseViewHolder {

    @BindView(R.id.loadmore_progress)
    ProgressBar progressBar;

    public LoadingViewHolder(View itemView) {
        super(itemView);
    }
}
