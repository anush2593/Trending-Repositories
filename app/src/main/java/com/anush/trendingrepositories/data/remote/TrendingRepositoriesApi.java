package com.anush.trendingrepositories.data.remote;

import com.anush.trendingrepositories.data.entities.RepositoriesResponse;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TrendingRepositoriesApi {

    @GET("search/repositories?sort=stars&order=desc")
    Single<RepositoriesResponse> getRepositories(@Query("q") String minDate, @Query("page") Integer page);
}
