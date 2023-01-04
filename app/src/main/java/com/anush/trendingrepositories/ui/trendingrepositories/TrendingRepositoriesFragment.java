package com.anush.trendingrepositories.ui.trendingrepositories;

import static autodispose2.AutoDispose.autoDisposable;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.LoadState;
import androidx.recyclerview.widget.ConcatAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.anush.trendingrepositories.data.remote.NoInternetConnectionException;
import com.anush.trendingrepositories.databinding.FragmentTrendingRepositoriesBinding;

import autodispose2.androidx.lifecycle.AndroidLifecycleScopeProvider;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class TrendingRepositoriesFragment extends Fragment {

    private FragmentTrendingRepositoriesBinding binding;

    private TrendingRepositoriesViewModel trendingRepositoriesViewModel;

    private TrendingRepositoriesAdapter trendingRepositoriesAdapter;


    public TrendingRepositoriesFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentTrendingRepositoriesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupTrendingRepositoriesRecyclerView();
        initViewModel();
        addObservers();
        initViewListeners();


        trendingRepositoriesAdapter.addLoadStateListener(combinedLoadStates -> {

            if (combinedLoadStates.getRefresh() instanceof LoadState.Error) {
                LoadState.Error loadStateError = (LoadState.Error) combinedLoadStates.getRefresh();
                if (loadStateError.getError() instanceof NoInternetConnectionException) {
                    trendingRepositoriesViewModel.setNoDataMutableLiveData(true);
                }
            }
            if (combinedLoadStates.getRefresh() instanceof LoadState.NotLoading) {
                trendingRepositoriesViewModel.setLoadingMutableLiveData(false);
            }
            return null;
        });

    }

    private void initViewListeners() {
        binding.spinnerTimeframe.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                trendingRepositoriesViewModel.getTrendingRepositoriesByMinDate(position);
                trendingRepositoriesViewModel.getTrendingRepositoriesByMinDate2(position)
                        .to(autoDisposable(AndroidLifecycleScopeProvider.from(requireActivity())))
                        .subscribe(pagingData -> trendingRepositoriesAdapter.submitData(getLifecycle(), pagingData));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        binding.ivRefresh.setOnClickListener(v -> {
            trendingRepositoriesViewModel.getTrendingRepositoriesByMinDate(binding.spinnerTimeframe.getSelectedItemPosition());
        });
    }

    private void addObservers() {
        trendingRepositoriesViewModel.getTrendingRepositoriesLiveData().observe(getViewLifecycleOwner(), repositories -> {
//            trendingRepositoriesAdapter.submitList(repositories);
        });

        trendingRepositoriesViewModel.getLoadingLiveData().observe(getViewLifecycleOwner(), isLoading -> {
            if (isLoading) {
                binding.progressBar.setVisibility(View.VISIBLE);
            } else {
                binding.progressBar.setVisibility(View.GONE);
            }
        });

        trendingRepositoriesViewModel.getNoDataLiveData().observe(getViewLifecycleOwner(), isNoData -> {
            if (isNoData) {
                binding.tvNoData.setVisibility(View.VISIBLE);
            } else {
                binding.tvNoData.setVisibility(View.GONE);
            }
        });

        trendingRepositoriesViewModel.getRefreshLiveData().observe(getViewLifecycleOwner(), refresh -> {
            if (refresh) {
                binding.ivRefresh.setVisibility(View.VISIBLE);
            } else {
                binding.ivRefresh.setVisibility(View.GONE);
            }
        });

        trendingRepositoriesViewModel.getErrorMessageLiveData().observe(getViewLifecycleOwner(), errorMessage -> {
            if (errorMessage != null) {
                binding.rvTrendingRepositories.setVisibility(View.GONE);
                binding.tvErrorMessage.setVisibility(View.VISIBLE);
                binding.tvErrorMessage.setText(String.format("Error: %s", errorMessage));
            } else {
                binding.rvTrendingRepositories.setVisibility(View.VISIBLE);
                binding.tvErrorMessage.setVisibility(View.GONE);
            }
        });
    }

    private void setupTrendingRepositoriesRecyclerView() {
        trendingRepositoriesAdapter = new TrendingRepositoriesAdapter();
        ConcatAdapter trendingRepositoriesAdapterWithFooter = trendingRepositoriesAdapter.withLoadStateFooter(new TrendingRepositoriesLoadStateAdapter(v -> {
            trendingRepositoriesViewModel.getTrendingRepositoriesByMinDate2(binding.spinnerTimeframe.getSelectedItemPosition());
        }));
        binding.rvTrendingRepositories.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));
        binding.rvTrendingRepositories.setAdapter(trendingRepositoriesAdapterWithFooter);
    }

    private void initViewModel() {
        trendingRepositoriesViewModel = new ViewModelProvider(this).get(TrendingRepositoriesViewModel.class);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}