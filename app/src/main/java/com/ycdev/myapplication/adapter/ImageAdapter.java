package com.ycdev.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ycdev.myapplication.R;
import com.ycdev.myapplication.model.Photo;
import com.ycdev.myapplication.utils.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageHolder> {
    private List<Photo> photos = new ArrayList<>();
    private OnImageClickListener listener;

    public ImageAdapter(OnImageClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ImageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.grid_item, parent, false);
        return new ImageHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageHolder holder, int position) {
        Photo photo = photos.get(position);
        holder.tvId.setText(String.valueOf(photo.getId()));
        holder.tvTitle.setText(photo.getTitle());

        ImageLoader.getInstance().loadImageByUrl(holder.ivThumbnail, photo.getThumbnailUrl());
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
        notifyDataSetChanged();
    }

    class ImageHolder extends RecyclerView.ViewHolder {
        private TextView tvId;
        private TextView tvTitle;
        private ImageView ivThumbnail;

        private ImageHolder(@NonNull View itemView, OnImageClickListener listener) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tv_id);
            tvTitle = itemView.findViewById(R.id.tv_title);
            ivThumbnail = itemView.findViewById(R.id.iv_thumbnail);
            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                Photo photo = photos.get(position);

                listener.onImageClicked(photo);
            });
        }
    }

    public interface OnImageClickListener {
        void onImageClicked(Photo photo);
    }
}
