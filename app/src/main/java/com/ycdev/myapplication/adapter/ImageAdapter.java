package com.ycdev.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ycdev.myapplication.databinding.GridItemBinding;
import com.ycdev.myapplication.model.Photo;

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
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        GridItemBinding gridItemBinding = GridItemBinding.inflate(layoutInflater, parent, false);
        return new ImageHolder(gridItemBinding, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageHolder holder, int position) {
        holder.onBind(position);
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
        private GridItemBinding binding;
        private Photo photo;

        private ImageHolder(GridItemBinding binding, OnImageClickListener listener) {
            super(binding.getRoot());
            this.binding = binding;

            itemView.setOnClickListener(v -> {
                listener.onImageClicked(photo);
            });
        }

        private void onBind(int position) {
            photo = photos.get(position);
            binding.setPhoto(photo);
        }
    }

    public interface OnImageClickListener {
        void onImageClicked(Photo photo);
    }
}
