package com.anush.trendingrepositories.data.remote;


import androidx.annotation.NonNull;
import androidx.paging.Pager;
import androidx.paging.PagingConfig;
import androidx.paging.PagingData;
import androidx.paging.rxjava3.PagingRx;

import com.anush.trendingrepositories.models.Repository;
import com.anush.trendingrepositories.ui.trendingrepositories.TrendingRepositoriesPagingSource;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import io.reactivex.rxjava3.core.Flowable;

public class RemoteDataSourceImpl implements RemoteDataSource {
    public static final int NETWORK_PAGE_SIZE = 30;


    TrendingRepositoriesApi trendingRepositoriesApi;

    public RemoteDataSourceImpl(TrendingRepositoriesApi trendingRepositoriesApi) {
        this.trendingRepositoriesApi = trendingRepositoriesApi;

    }

    @Override
    public Flowable<PagingData<Repository>> getTrendingRepositoriesByMinDate(Date date) {
        final String q = "created:>=" + dateToString(date);

        Pager<Integer, Repository> pager = new Pager<>(new PagingConfig(NETWORK_PAGE_SIZE), () -> new TrendingRepositoriesPagingSource(trendingRepositoriesApi, q));
        Flowable<PagingData<Repository>> flowable = PagingRx.getFlowable(pager);

        return flowable;
    }

    @NonNull
    private String dateToString(Date date) {
        final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return dateFormat.format(date);
    }
}
