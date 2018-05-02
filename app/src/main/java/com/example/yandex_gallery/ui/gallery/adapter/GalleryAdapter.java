package com.example.yandex_gallery.ui.gallery.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.example.yandex_gallery.data.models.Item;
import com.example.yandex_gallery.di.ActivityContext;
import com.example.yandex_gallery.ui.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class GalleryAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private static final int ITEM = 0, LOADING = 1;

    private Context context;
    private final List<Item> items;
    private OnImageClickListener listener;

    private boolean isLoadingAdded = false;

    @Inject
    GalleryAdapter(@ActivityContext Context context) {
        this.context = context;
        items = new ArrayList<>();
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case ITEM:
                GalleryViewHolder vh = GalleryViewHolder.inflate(parent);
                vh.container.setOnClickListener(view ->
                        listener.onImageClick(vh.getAdapterPosition()));
                return vh;
            case LOADING:
                return LoadingViewHolder.inflate(parent);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case ITEM:
                bindItemViewHolder((GalleryViewHolder) holder, holder.getAdapterPosition());
                break;
            case LOADING:
                bindLoadingViewHolder((LoadingViewHolder) holder);
                break;
        }

    }

    private void bindItemViewHolder(GalleryViewHolder vh, int aPosition) {
        //Timber.e("Load " + aPosition + " " + items.get(aPosition).getFile());
        Glide
                .with(context)
                .load(items.get(aPosition).getFile())
                .apply(new RequestOptions().centerCrop())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        vh.itemProgress.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        vh.itemProgress.setVisibility(View.GONE);
                        return false;
                    }
                })
                .into(vh.image);
    }

    private void bindLoadingViewHolder(LoadingViewHolder vh) {
        vh.progressBar.setVisibility(View.VISIBLE);
    }

    public void setOnImageClickListener(OnImageClickListener listener) {
        this.listener = listener;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        return (position == items.size() - 1 && isLoadingAdded) ? LOADING : ITEM;
    }

    public void add(Item item) {
        this.items.add(item);
        notifyItemInserted(items.size() - 1);
    }

    public void addAll(List<Item> items) {
        for (Item item : items) {
            add(item);
        }
    }

    public void setData(List<Item> items) {
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    public void addLoadingFooter() {
        isLoadingAdded = true;
        add(new Item());
    }

    public void removeLoadingFooter() {
        isLoadingAdded = false;

        int position = items.size() - 1;
        Item item = getItem(position);

        if (item != null) {
            items.remove(position);
            notifyItemRemoved(position);
        }
    }

    private Item getItem(int position) {
        return items.get(position);
    }

    public interface OnImageClickListener {
        void onImageClick(int imageId);
    }
}
