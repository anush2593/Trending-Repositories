package com.anush.trendingrepositories.repository;

import androidx.paging.PagingData;

import com.anush.trendingrepositories.RepositoryMapper;
import com.anush.trendingrepositories.data.remote.RemoteDataSource;
import com.anush.trendingrepositories.models.Repository;

import java.util.Date;

import io.reactivex.rxjava3.core.Flowable;

public class DataRepositoryImpl implements DataRepository {

    private final RemoteDataSource mRemoteDataSource;
    private final RepositoryMapper mRepositoryMapper;

    public DataRepositoryImpl(RemoteDataSource remoteDataSource, RepositoryMapper repositoryMapper) {
        mRemoteDataSource = remoteDataSource;
        mRepositoryMapper = repositoryMapper;
    }

    @Override
    public Flowable<PagingData<Repository>> getTrendingRepositoriesByMinDate(Date date) {


        return mRemoteDataSource.getTrendingRepositoriesByMinDate(date);
//       return mRemoteDataSource.getTrendingRepositoriesByMinDate(date)
//               .map(result -> mRepositoryMapper.mapToRepositoryList(result.getItems()));
    }
}
