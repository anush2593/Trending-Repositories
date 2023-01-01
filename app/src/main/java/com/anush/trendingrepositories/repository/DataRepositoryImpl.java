package com.anush.trendingrepositories.repository;

import com.anush.trendingrepositories.data.remote.RemoteDataSource;

public class DataRepositoryImpl implements DataRepository {

    private final RemoteDataSource mRemoteDataSource;

    public DataRepositoryImpl(RemoteDataSource remoteDataSource) {
        mRemoteDataSource = remoteDataSource;
    }
}
