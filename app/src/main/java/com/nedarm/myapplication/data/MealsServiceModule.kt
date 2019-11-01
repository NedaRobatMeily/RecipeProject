package com.nedarm.myapplication.data

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class MealsServiceModule {
    @Provides
    @Singleton
    fun provideMealsService(retrofit: Retrofit): MealsService {
        return retrofit.create(MealsService::class.java)
    }
}
