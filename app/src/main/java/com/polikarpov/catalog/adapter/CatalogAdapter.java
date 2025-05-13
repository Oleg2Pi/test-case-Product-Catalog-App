package com.polikarpov.catalog.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.polikarpov.catalog.R;
import com.polikarpov.catalog.model.CatalogItem;

import java.util.List;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CatalogAdapter extends RecyclerView.Adapter<CatalogAdapter.CatalogViewHolder>{

    private final List<CatalogItem> items;
    private final OnItemClickListener listener;

    @NonNull
    @Override
    public CatalogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_catalog, parent, false);
        return new CatalogViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CatalogViewHolder holder, int position) {
        CatalogItem item = items.get(position);
        holder.bind(item, listener);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class CatalogViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imageView;
        private final TextView titleTextView;
        private final TextView descriptionTextView;

        public CatalogViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.item_image);
            titleTextView = itemView.findViewById(R.id.item_title);
            descriptionTextView = itemView.findViewById(R.id.item_description);
        }

        public void bind(final CatalogItem item, final OnItemClickListener listener) {
            titleTextView.setText(item.getName());
            descriptionTextView.setText(item.getDescription());

            Glide.with(itemView.getContext())
                    .load(item.getImage())
                    .placeholder(R.drawable.placeholder)
                    .into(imageView);

            itemView.setOnClickListener(v -> listener.onItemClick(item));
        }
    }

}
