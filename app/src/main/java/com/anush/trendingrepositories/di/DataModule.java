package com.anush.trendingrepositories.di;

import com.anush.trendingrepositories.RepositoryMapper;
import com.anush.trendingrepositories.data.remote.RemoteDataSource;
import com.anush.trendingrepositories.data.remote.RemoteDataSourceImpl;
import com.anush.trendingrepositories.data.remote.TrendingRepositoriesApi;
import com.anush.trendingrepositories.repository.DataRepository;
import com.anush.trendingrepositories.repository.DataRepositoryImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class DataModule {

    @Provides
    @Singleton
    RemoteDataSource provideRemoteDataSource(TrendingRepositoriesApi trendingRepositoriesApi) {
        return new RemoteDataSourceImpl(trendingRepositoriesApi);
    }

    @Provides
    @Singleton
    DataRepository provideDataRepository(RemoteDataSource remoteDataSource, RepositoryMapper repositoryMapper) {
        return new DataRepositoryImpl(remoteDataSource, repositoryMapper);
    }

}