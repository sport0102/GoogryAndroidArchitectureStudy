package com.aiden.aiden.architecturepatternstudy.di

import com.aiden.aiden.architecturepatternstudy.ui.main.MainSearchViewModel
import com.aiden.aiden.architecturepatternstudy.ui.main.MainTickerViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun getAppModule() = module {
    viewModel { MainSearchViewModel() }
    viewModel { MainTickerViewModel(get()) }
}