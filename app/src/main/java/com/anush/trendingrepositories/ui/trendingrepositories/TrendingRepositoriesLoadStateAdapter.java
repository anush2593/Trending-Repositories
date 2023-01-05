package com.anush.trendingrepositories.ui.trendingrepositories;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.LoadState;
import androidx.paging.LoadStateAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.anush.trendingrepositories.R;
import com.anush.trendingrepositories.databinding.ItemLoadStateBinding;

import org.jetbrains.annotations.NotNull;

class TrendingRepositoriesLoadStateAdapter extends LoadStateAdapter<TrendingRepositoriesLoadStateAdapter.LoadStateViewHolder> {
    private final View.OnClickListener mRetryCallback;

    TrendingRepositoriesLoadStateAdapter(View.OnClickListener retryCallback) {
        mRetryCallback = retryCallback;
    }

    @NotNull
    @Override
    public LoadStateViewHolder onCreateViewHolder(@NotNull ViewGroup parent, @NotNull LoadState loadState) {
        return new LoadStateViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(@NotNull LoadStateViewHolder holder, @NotNull LoadState loadState) {
        holder.bind(loadState, mRetryCallback);
    }


    public static class LoadStateViewHolder extends RecyclerView.ViewHolder {
        private ProgressBar mProgressBar;
        private TextView mErrorMsg;
        private ImageView mRetry;

        LoadStateViewHolder(@NonNull ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_load_state, parent, false));
            ItemLoadStateBinding binding = ItemLoadStateBinding.bind(itemView);

            mProgressBar = binding.progressBarItem;
            mErrorMsg = binding.tvErrorItem;
            mRetry = binding.ivRefreshItem;
        }

        public void bind(LoadState loadState, @NonNull View.OnClickListener retryCallback) {
            if (loadState instanceof LoadState.Error) {
                LoadState.Error loadStateError = (LoadState.Error) loadState;
                mErrorMsg.setText(loadStateError.getError().getLocalizedMessage());
            }
            mProgressBar.setVisibility(loadState instanceof LoadState.Loading ? View.VISIBLE : View.GONE);
            mRetry.setVisibility(loadState instanceof LoadState.Error ? View.VISIBLE : View.GONE);
            mRetry.setOnClickListener(retryCallback);
            mErrorMsg.setVisibility(loadState instanceof LoadState.Error ? View.VISIBLE : View.GONE);
        }
    }
}
