package com.anush.trendingrepositories.data.remote;

import com.anush.trendingrepositories.data.entities.RepositoriesResponse;

import retrofit2.http.GET;
import retrofit2.http.Path;

public interface TrendingRepositoriesApi {

    @GET("search/repositories?q=created:>{date}&sort=stars&order=desc")
    RepositoriesResponse getRepositories(@Path("date") String minDate);
}
