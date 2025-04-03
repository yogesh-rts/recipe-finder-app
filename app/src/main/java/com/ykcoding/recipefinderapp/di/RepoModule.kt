package com.ykcoding.recipefinderapp.di

import com.ykcoding.recipefinderapp.data.repo.RecipesRepoImpl
import com.ykcoding.recipefinderapp.domain.repo.RecipesRepo
import org.koin.dsl.module

val repoModule = module {
    single<RecipesRepo> { RecipesRepoImpl(service = get()) }
}