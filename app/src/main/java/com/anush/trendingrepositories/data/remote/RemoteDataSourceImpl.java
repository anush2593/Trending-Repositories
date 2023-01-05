package com.anush.trendingrepositories.data.remote;


import androidx.paging.Pager;
import androidx.paging.PagingConfig;
import androidx.paging.PagingData;
import androidx.paging.rxjava3.PagingRx;

import com.anush.trendingrepositories.helpers.DateHelper;
import com.anush.trendingrepositories.models.Repository;
import com.anush.trendingrepositories.data.pagination.TrendingRepositoriesPagingSource;

import java.util.Date;

import io.reactivex.rxjava3.core.Flowable;

public class RemoteDataSourceImpl implements RemoteDataSource {
    public static final int NETWORK_PAGE_SIZE = 30;


    TrendingRepositoriesApi trendingRepositoriesApi;

    public RemoteDataSourceImpl(TrendingRepositoriesApi trendingRepositoriesApi) {
        this.trendingRepositoriesApi = trendingRepositoriesApi;

    }

    @Override
    public Flowable<PagingData<Repository>> getTrendingRepositoriesByMinDate(Date date) {
        final String q = "created:>=" + DateHelper.dateToString(date);

        Pager<Integer, Repository> pager = new Pager<>(new PagingConfig(NETWORK_PAGE_SIZE), () -> new TrendingRepositoriesPagingSource(trendingRepositoriesApi, q));
        return PagingRx.getFlowable(pager);
    }
}
