package com.example.yandex_gallery.ui.gallery.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.yandex_gallery.R;
import com.example.yandex_gallery.ui.base.BaseViewHolder;

import butterknife.BindView;

public class GalleryViewHolder extends BaseViewHolder {

    public static GalleryViewHolder inflate(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.gallery_item, parent, false);
        return new GalleryViewHolder(view);
    }

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
