package com.example.kmmjsonplaceholderapp.android.di

import com.example.kmmjsonplaceholderapp.android.presentation.detail.DetailViewModel
import com.example.kmmjsonplaceholderapp.android.presentation.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val viewModelModule = module {
    viewModel {
        HomeViewModel(get())
    }

    viewModel {
        DetailViewModel(get(),get())
    }
}