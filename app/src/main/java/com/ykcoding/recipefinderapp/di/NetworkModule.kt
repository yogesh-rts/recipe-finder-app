package com.ykcoding.recipefinderapp.di

import com.squareup.moshi.Moshi
import com.ykcoding.recipefinderapp.BuildConfig
import com.ykcoding.recipefinderapp.data.ServerManager
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

private const val TIME_OUT = 15

val networkModule = module {

    single {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }
    single { Moshi.Builder().build() }
    single {
        OkHttpClient.Builder()
            .addInterceptor(get<HttpLoggingInterceptor>())
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("Authorization", BuildConfig.API_KEYS)
                    .build()
                chain.proceed(request)
            }
            .connectTimeout(TIME_OUT.toLong(), TimeUnit.MILLISECONDS)
            .readTimeout(TIME_OUT.toLong(), TimeUnit.MILLISECONDS)
            .writeTimeout(TIME_OUT.toLong(), TimeUnit.MILLISECONDS)
            .retryOnConnectionFailure(true)
            .build()
    }
    single { provideRetrofit(get(), get()) }

}

private fun provideRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit {
    return Retrofit.Builder()
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(ServerManager.url)
        .build()
}