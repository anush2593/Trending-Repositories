package com.anush.trendingrepositories.data.remote;


import androidx.annotation.NonNull;

import com.anush.trendingrepositories.data.entities.RepositoriesResponse;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import io.reactivex.rxjava3.core.Single;

public class RemoteDataSourceImpl implements RemoteDataSource {

    TrendingRepositoriesApi trendingRepositoriesApi;

    public RemoteDataSourceImpl(TrendingRepositoriesApi trendingRepositoriesApi) {
        this.trendingRepositoriesApi = trendingRepositoriesApi;

    }

    @Override
    public Single<RepositoriesResponse> getTrendingRepositoriesByMinDate(Date date) {
        final String q = "created:>=" + dateToString(date);
        return trendingRepositoriesApi.getRepositories(q);
    }

    @NonNull
    private String dateToString(Date date) {
        final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return dateFormat.format(date);
    }
}
