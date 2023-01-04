package com.anush.trendingrepositories.data.remote;


import androidx.paging.PagingData;

import com.anush.trendingrepositories.models.Repository;

import java.util.Date;

import io.reactivex.rxjava3.core.Flowable;

public interface RemoteDataSource {

    Flowable<PagingData<Repository>> getTrendingRepositoriesByMinDate(Date date);

}
