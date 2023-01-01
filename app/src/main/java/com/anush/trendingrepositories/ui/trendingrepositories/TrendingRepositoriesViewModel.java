package com.anush.trendingrepositories.ui.trendingrepositories;

import androidx.lifecycle.ViewModel;

import com.anush.trendingrepositories.repository.DataRepository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class TrendingRepositoriesViewModel extends ViewModel {

    @Inject
    public TrendingRepositoriesViewModel(DataRepository repository) {
    }
}
