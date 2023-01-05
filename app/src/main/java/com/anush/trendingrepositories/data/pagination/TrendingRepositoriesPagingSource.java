package com.anush.trendingrepositories.data.pagination;

import static com.anush.trendingrepositories.data.remote.RemoteDataSourceImpl.NETWORK_PAGE_SIZE;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.paging.PagingState;
import androidx.paging.rxjava3.RxPagingSource;

import com.anush.trendingrepositories.RepositoryMapper;
import com.anush.trendingrepositories.data.entities.RepositoriesResponse;
import com.anush.trendingrepositories.data.remote.TrendingRepositoriesApi;
import com.anush.trendingrepositories.models.Repository;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class TrendingRepositoriesPagingSource extends RxPagingSource<Integer, Repository> {
    @NonNull
    private final TrendingRepositoriesApi mBackend;
    @NonNull
    private final String mQuery;

    private static final int STARTING_PAGE_INDEX = 1;


    public TrendingRepositoriesPagingSource(@NonNull TrendingRepositoriesApi backend, @NonNull String query) {
        mBackend = backend;
        mQuery = query;
    }

    @NotNull
    @Override
    public Single<LoadResult<Integer, Repository>> loadSingle(@NotNull LoadParams<Integer> params) {
        Integer nextPageNumber = params.getKey();
        if (nextPageNumber == null) {
            nextPageNumber = 1;
        }

        return mBackend.getRepositories(mQuery, nextPageNumber)
                .subscribeOn(Schedulers.io())
                .map(response -> toLoadResult(response, params))
                .onErrorReturn(LoadResult.Error::new);
    }

    private LoadResult<Integer, Repository> toLoadResult(@NonNull RepositoriesResponse response, LoadParams<Integer> params) {
        List<Repository> repositories = new RepositoryMapper().mapToRepositoryList(response.getItems());

        int position = (params.getKey() != null) ? params.getKey() : STARTING_PAGE_INDEX;
        Integer prevKey = (position == STARTING_PAGE_INDEX) ? null : position - 1;
        Integer nextKey = (repositories.isEmpty()) ? null : position + (params.getLoadSize() / NETWORK_PAGE_SIZE);

        return new LoadResult.Page<>(
                repositories,
                prevKey,
                nextKey,
                LoadResult.Page.COUNT_UNDEFINED,
                LoadResult.Page.COUNT_UNDEFINED);
    }

    @Nullable
    @Override
    public Integer getRefreshKey(@NotNull PagingState<Integer, Repository> state) {
        Integer anchorPosition = state.getAnchorPosition();
        if (anchorPosition == null) {
            return null;
        }

        LoadResult.Page<Integer, Repository> anchorPage = state.closestPageToPosition(anchorPosition);
        if (anchorPage == null) {
            return null;
        }

        Integer prevKey = anchorPage.getPrevKey();
        if (prevKey != null) {
            return prevKey + 1;
        }

        Integer nextKey = anchorPage.getNextKey();
        if (nextKey != null) {
            return nextKey - 1;
        }

        return null;
    }
}
