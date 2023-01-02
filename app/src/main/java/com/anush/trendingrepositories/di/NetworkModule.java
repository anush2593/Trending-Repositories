package com.anush.trendingrepositories.di;

import com.anush.trendingrepositories.data.remote.TrendingRepositoriesApi;
import com.anush.trendingrepositories.data.remote.TrendingRepositoriesInterceptor;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public class NetworkModule {

    @Provides
    TrendingRepositoriesApi provideTrendingRepositoriesApi(Retrofit retrofit) {
        return retrofit.create(TrendingRepositoriesApi.class);
    }

    @Provides
    OkHttpClient provideOkHttpClient(TrendingRepositoriesInterceptor trendingRepositoriesInterceptor) {
        return new OkHttpClient().newBuilder()
                .addInterceptor(trendingRepositoriesInterceptor)
                .build();
    }

    @Provides
    Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
    }
}
