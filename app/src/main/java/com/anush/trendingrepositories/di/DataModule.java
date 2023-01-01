package com.anush.trendingrepositories.di;

import com.anush.trendingrepositories.data.RemoteDataSource;
import com.anush.trendingrepositories.data.RemoteDataSourceImpl;
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
    RemoteDataSource provideRemoteDataSource() {
        return new RemoteDataSourceImpl();
    }

    @Provides
    @Singleton
    DataRepository provideDataRepository(RemoteDataSource remoteDataSource) {
        return new DataRepositoryImpl(remoteDataSource);
    }

}