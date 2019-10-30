package com.nedarm.myapplication.networking

import dagger.Module
import dagger.Provides
import okhttp3.Call
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {
    @Provides
    @Singleton
    fun provideOkHttp(): Call.Factory {
        return OkHttpClient.Builder().build()
    }

    @Provides
    @Named("base_url")
    fun provideBaseUrl(): String {
        return "https://api.edamam.com/"
    }
}