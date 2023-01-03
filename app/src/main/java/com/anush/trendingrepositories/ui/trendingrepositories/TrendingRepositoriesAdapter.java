package com.anush.trendingrepositories.ui.trendingrepositories;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.anush.trendingrepositories.R;
import com.anush.trendingrepositories.databinding.ItemRepositoryBinding;
import com.anush.trendingrepositories.models.Repository;
import com.bumptech.glide.Glide;

public class TrendingRepositoriesAdapter extends ListAdapter<Repository, TrendingRepositoriesAdapter.ViewHolder> {
    protected TrendingRepositoriesAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemRepositoryBinding binding = ItemRepositoryBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Repository repository = getItem(position);

        holder.binding.tvRepositoryName.setText(repository.getName());
        holder.binding.tvRepositoryDescription.setText(repository.getDescriptionFormatted());
        holder.binding.tvUsername.setText(repository.getOwner().getUsername());
        holder.binding.tvStarCount.setText(String.valueOf(repository.getStarsCount()));
        Glide.with(holder.binding.ivAvatar.getContext())
                .load(repository.getOwner().getAvatarUrl())
                .placeholder(R.drawable.ic_no_image)
                .into(holder.binding.ivAvatar);
    }

    @Override
    public int getItemCount() {
        return getCurrentList().size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ItemRepositoryBinding binding;

        ViewHolder(ItemRepositoryBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public static final DiffUtil.ItemCallback<Repository> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Repository>() {
                @Override
                public boolean areItemsTheSame(@NonNull Repository oldItem, @NonNull Repository newItem) {
                    return oldItem.getId().equals(newItem.getId());
                }

                @Override
                public boolean areContentsTheSame(@NonNull Repository oldItem, @NonNull Repository newItem) {
                    return oldItem.equals(newItem);
                }
            };
}
