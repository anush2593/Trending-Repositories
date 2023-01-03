package com.anush.trendingrepositories.ui.trendingrepositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.anush.trendingrepositories.helpers.DateHelper;
import com.anush.trendingrepositories.models.Repository;
import com.anush.trendingrepositories.repository.DataRepository;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

@HiltViewModel
public class TrendingRepositoriesViewModel extends ViewModel {
    public static final int LAST_DAY = 0;
    public static final int LAST_WEEK = 1;
    public static final int LAST_MONTH = 2;

    private final DataRepository repository;

    private final MutableLiveData<List<Repository>> trendingRepositoriesMutableLiveData = new MutableLiveData<>();
    public final LiveData<List<Repository>> trendingRepositoriesLiveData = trendingRepositoriesMutableLiveData;

    private final MutableLiveData<Boolean> loadingMutableLiveData = new MutableLiveData<>();
    public final LiveData<Boolean> loadingLiveData = loadingMutableLiveData;

    private final MutableLiveData<Boolean> noDataMutableLiveData = new MutableLiveData<>();
    public final LiveData<Boolean> noDataLiveData = noDataMutableLiveData;

    private final MutableLiveData<String> errorMessageMutableLiveData = new MutableLiveData<>();
    public final LiveData<String> errorMessageLiveData = errorMessageMutableLiveData;

    @Inject
    public TrendingRepositoriesViewModel(DataRepository repository) {
        this.repository = repository;
    }

    public void getTrendingRepositoriesByMinDate(int i) {
        setLoadingMutableLiveData(true);

        Date minDate = null;
        switch (i) {
            case LAST_DAY:
                minDate = DateHelper.getDateOneDayAgo();
                break;
            case LAST_WEEK:
                minDate = DateHelper.getDateWeekAgo();
                break;
            case LAST_MONTH:
                minDate = DateHelper.getDateMonthAgo();
                break;
        }


        repository.getTrendingRepositoriesByMinDate(minDate)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(trendingRepositories -> {
                    setLoadingMutableLiveData(false);
                    if (trendingRepositories.isEmpty()) {
                        setNoDataMutableLiveData(true);
                    } else {
                        setNoDataMutableLiveData(false);
                        setTrendingRepositoriesMutableLiveData(trendingRepositories);
                    }
                }, throwable -> {
                    setLoadingMutableLiveData(false);
                    setErrorMessageMutableLiveData(throwable.getMessage());
                });
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

    private MutableLiveData<Boolean> getLoadingMutableLiveData() {
        return loadingMutableLiveData;
    }

    private void setLoadingMutableLiveData(Boolean loadingMutableLiveData) {
        this.loadingMutableLiveData.setValue(loadingMutableLiveData);
    }

    public LiveData<Boolean> getLoadingLiveData() {
        return loadingLiveData;
    }

    private MutableLiveData<Boolean> getNoDataMutableLiveData() {
        return noDataMutableLiveData;
    }

    private void setNoDataMutableLiveData(Boolean noDataMutableLiveData) {
        this.noDataMutableLiveData.setValue(noDataMutableLiveData);
    }

    public LiveData<Boolean> getNoDataLiveData() {
        return noDataLiveData;
    }

    private MutableLiveData<String> getErrorMessageMutableLiveData() {
        return errorMessageMutableLiveData;
    }

    private void setErrorMessageMutableLiveData(String errorMessageMutableLiveData) {
        this.errorMessageMutableLiveData.setValue(errorMessageMutableLiveData);
    }

    public LiveData<String> getErrorMessageLiveData() {
        return errorMessageLiveData;
    }
}
