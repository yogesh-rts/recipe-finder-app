package com.ykcoding.recipefinderapp.di

import com.ykcoding.recipefinderapp.presentation.RecipesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { RecipesViewModel(recipesUseCase = get()) }
}