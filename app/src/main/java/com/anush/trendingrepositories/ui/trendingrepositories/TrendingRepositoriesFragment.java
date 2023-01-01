package com.anush.trendingrepositories.ui.trendingrepositories;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.anush.trendingrepositories.databinding.FragmentTrendingRepositoriesBinding;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class TrendingRepositoriesFragment extends Fragment {

    private FragmentTrendingRepositoriesBinding binding;

    private TrendingRepositoriesViewModel trendingRepositoriesViewModel;

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

        initViewModel();
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