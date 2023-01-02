package com.anush.trendingrepositories.data.remote;


import com.anush.trendingrepositories.data.entities.RepositoriesResponse;

import java.util.Date;

import io.reactivex.rxjava3.core.Single;

public interface RemoteDataSource {

    Single<RepositoriesResponse> getTrendingRepositoriesByMinDate(Date date);

}
