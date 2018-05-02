package com.example.yandex_gallery.ui.gallery.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.yandex_gallery.R;
import com.example.yandex_gallery.ui.base.BaseViewHolder;

import butterknife.BindView;


public class LoadingViewHolder extends BaseViewHolder {

    public static LoadingViewHolder inflate(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.gallery_progress, parent, false);
        return new LoadingViewHolder(view);
    }

    @BindView(R.id.loadmore_progress)
    ProgressBar progressBar;

    public LoadingViewHolder(View itemView) {
        super(itemView);
    }
}
