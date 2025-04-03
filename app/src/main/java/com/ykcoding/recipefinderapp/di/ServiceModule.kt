package com.ykcoding.recipefinderapp.di

import com.ykcoding.recipefinderapp.data.service.recipes.Recipes
import org.koin.dsl.module
import retrofit2.Retrofit

val serviceModule = module {
    single<Recipes> { provideRetrofitService(retrofit = get()) }
}

private inline fun <reified T> provideRetrofitService(retrofit: Retrofit): T {
    return retrofit.create(T::class.java)
}