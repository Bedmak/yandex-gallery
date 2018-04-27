package com.example.yandex_gallery.ui.main.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.yandex_gallery.R;
import com.example.yandex_gallery.data.models.Item;
import com.example.yandex_gallery.di.ActivityContext;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryViewHolder> {

    private Context context;
    private final List<Item> items;

    @Inject
    GalleryAdapter(@ActivityContext Context context) {
        this.context = context;
        items = new ArrayList<>();
    }

    @NonNull
    @Override
    public GalleryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.image_item, parent, false);
        return new GalleryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GalleryViewHolder holder, int position) {
        Item item = items.get(position);
        Glide
                .with(context)
                .load(item.getFile())
                .into(holder.galleryImage);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setData(List<Item> items) {
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }
}
