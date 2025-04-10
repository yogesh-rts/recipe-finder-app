package com.ykcoding.recipefinderapp.di

import com.ykcoding.recipefinderapp.domain.repo.RecipesRepo
import com.ykcoding.recipefinderapp.domain.usecases.RandomRecipesUseCase
import com.ykcoding.recipefinderapp.domain.usecases.RecipesUseCase
import org.koin.dsl.module

val usecaseModule  = module {
    single { RecipesUseCase(recipesRepo = get<RecipesRepo>()) }
    single { RandomRecipesUseCase(randomRecipesRepo = get<RecipesRepo>()) }
}