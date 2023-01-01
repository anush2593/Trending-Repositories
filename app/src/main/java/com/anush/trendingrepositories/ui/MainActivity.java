package com.anush.trendingrepositories.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.anush.trendingrepositories.R;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}