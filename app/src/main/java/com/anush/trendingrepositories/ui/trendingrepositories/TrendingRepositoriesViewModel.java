package com.anush.trendingrepositories.ui.trendingrepositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import androidx.paging.PagingData;
import androidx.paging.rxjava3.PagingRx;

import com.anush.trendingrepositories.helpers.DateHelper;
import com.anush.trendingrepositories.models.Repository;
import com.anush.trendingrepositories.repository.DataRepository;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.core.Flowable;
import kotlinx.coroutines.CoroutineScope;

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

    private final MutableLiveData<Boolean> refreshMutableLiveData = new MutableLiveData<>();
    public final LiveData<Boolean> refreshLiveData = refreshMutableLiveData;

    @Inject
    public TrendingRepositoriesViewModel(DataRepository repository) {
        this.repository = repository;
    }


    public Flowable<PagingData<Repository>> getTrendingRepositoriesByMinDate(int i) {
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

        CoroutineScope viewModelScope = ViewModelKt.getViewModelScope(this);
        Flowable<PagingData<Repository>> repositoryFlowable = repository.getTrendingRepositoriesByMinDate(minDate);
        PagingRx.cachedIn(repositoryFlowable, viewModelScope);

        return repositoryFlowable;
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

    public void setLoadingMutableLiveData(Boolean loadingMutableLiveData) {
        this.loadingMutableLiveData.setValue(loadingMutableLiveData);
    }

    public LiveData<Boolean> getLoadingLiveData() {
        return loadingLiveData;
    }

    private MutableLiveData<Boolean> getNoDataMutableLiveData() {
        return noDataMutableLiveData;
    }

    public void setNoDataMutableLiveData(Boolean noDataMutableLiveData) {
        this.noDataMutableLiveData.setValue(noDataMutableLiveData);
    }

    public LiveData<Boolean> getNoDataLiveData() {
        return noDataLiveData;
    }

    private MutableLiveData<String> getErrorMessageMutableLiveData() {
        return errorMessageMutableLiveData;
    }

    public void setErrorMessageMutableLiveData(String errorMessageMutableLiveData) {
        this.errorMessageMutableLiveData.setValue(errorMessageMutableLiveData);
    }

    public LiveData<String> getErrorMessageLiveData() {
        return errorMessageLiveData;
    }

    public MutableLiveData<Boolean> getRefreshMutableLiveData() {
        return refreshMutableLiveData;
    }

    public void setRefreshMutableLiveData(Boolean refreshMutableLiveData) {
        this.refreshMutableLiveData.setValue(refreshMutableLiveData);
    }

    public LiveData<Boolean> getRefreshLiveData() {
        return refreshLiveData;
    }
}
