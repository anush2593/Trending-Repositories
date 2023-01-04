package com.anush.trendingrepositories.repository;


import androidx.paging.PagingData;

import com.anush.trendingrepositories.models.Repository;

import java.util.Date;

import io.reactivex.rxjava3.core.Flowable;

public interface DataRepository {

    Flowable<PagingData<Repository>> getTrendingRepositoriesByMinDate(Date date);

}
