package com.anand.checklocationapp.di

import com.anand.checklocationapp.presentation.locationList.LocationListViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val viewModelModule = module {
    viewModel { LocationListViewModel(get()) }
}
