package com.anush.trendingrepositories.repository;


import com.anush.trendingrepositories.models.Repository;

import java.util.Date;
import java.util.List;

import io.reactivex.rxjava3.core.Single;

public interface DataRepository {

    Single<List<Repository>> getTrendingRepositoriesByMinDate(Date date);

}
