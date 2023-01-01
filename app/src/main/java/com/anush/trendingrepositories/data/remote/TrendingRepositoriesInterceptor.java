package com.anush.trendingrepositories.data.remote;

import androidx.annotation.NonNull;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class TrendingRepositoriesInterceptor implements Interceptor {

    @Inject
    public TrendingRepositoriesInterceptor() {
    }

    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request().newBuilder()
                .addHeader("Authorization", "Bearer " + "token")
                .build();
        return chain.proceed(request);
    }
}
