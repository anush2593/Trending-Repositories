package com.anush.trendingrepositories.repository;

import androidx.paging.PagingData;

import com.anush.trendingrepositories.data.remote.RemoteDataSource;
import com.anush.trendingrepositories.models.Repository;

import java.util.Date;

import io.reactivex.rxjava3.core.Flowable;

public class DataRepositoryImpl implements DataRepository {

    private final RemoteDataSource mRemoteDataSource;

    public DataRepositoryImpl(RemoteDataSource remoteDataSource) {
        mRemoteDataSource = remoteDataSource;
    }

    @Override
    public Flowable<PagingData<Repository>> getTrendingRepositoriesByMinDate(Date date) {
        return mRemoteDataSource.getTrendingRepositoriesByMinDate(date);
    }
}
