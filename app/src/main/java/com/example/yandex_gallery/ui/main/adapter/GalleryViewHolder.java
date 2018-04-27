package com.example.yandex_gallery.ui.main.adapter;

import android.view.View;
import android.widget.ImageView;

import com.example.yandex_gallery.R;
import com.example.yandex_gallery.ui.base.BaseViewHolder;

import butterknife.BindView;

public class GalleryViewHolder extends BaseViewHolder {

    @BindView(R.id.galleryImage)
    ImageView galleryImage;

    public GalleryViewHolder(View itemView) {
        super(itemView);
    }

}
