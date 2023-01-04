package com.anush.trendingrepositories.data.remote;

import java.io.IOException;

public class NoInternetConnectionException extends IOException {

    @Override
    public String getMessage() {
        return "No internet connection";
    }

}
