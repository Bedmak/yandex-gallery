package com.example.yandex_gallery.ui.gallery.adapter;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.yandex_gallery.R;
import com.example.yandex_gallery.ui.base.BaseViewHolder;

import butterknife.BindView;

public class GalleryViewHolder extends BaseViewHolder {

    @BindView(R.id.itemContainer)
    FrameLayout container;

    @BindView(R.id.imageProgress)
    ProgressBar itemProgress;

    @BindView(R.id.image)
    ImageView image;

    public GalleryViewHolder(View itemView) {
        super(itemView);
    }

}
