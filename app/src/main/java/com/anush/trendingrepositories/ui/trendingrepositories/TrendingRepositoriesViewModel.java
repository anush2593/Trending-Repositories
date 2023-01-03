package com.anush.trendingrepositories.ui.trendingrepositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.anush.trendingrepositories.helpers.DateHelper;
import com.anush.trendingrepositories.models.Repository;
import com.anush.trendingrepositories.repository.DataRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

@HiltViewModel
public class TrendingRepositoriesViewModel extends ViewModel {

    private final MutableLiveData<List<Repository>> trendingRepositoriesMutableLiveData = new MutableLiveData<>();
    public LiveData<List<Repository>> trendingRepositoriesLiveData = trendingRepositoriesMutableLiveData;

    @Inject
    public TrendingRepositoriesViewModel(DataRepository repository) {
        repository.getTrendingRepositoriesByMinDate(DateHelper.getDateOneDayAgo())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(x -> {
                    //TODO show error message
                })
                .doOnSuccess(trendingRepositories -> {
                    //TODO disable loading indicator, handle empty list case
                    setTrendingRepositoriesMutableLiveData(trendingRepositories);
                })
                .subscribe();
    }


    private MutableLiveData<List<Repository>> getTrendingRepositoriesMutableLiveData() {
        return trendingRepositoriesMutableLiveData;
    }

    private void setTrendingRepositoriesMutableLiveData(List<Repository> trendingRepositoriesMutableLiveData) {
        this.trendingRepositoriesMutableLiveData.setValue(trendingRepositoriesMutableLiveData);
    }

    public LiveData<List<Repository>> getTrendingRepositoriesLiveData() {
        return trendingRepositoriesLiveData;
    }

}
