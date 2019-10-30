package com.nedarm.myapplication.base

import android.app.Application
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.nedarm.myapplication.MainActivity
import com.nedarm.myapplication.di.NavHostModule
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module
abstract class AppModule {

    @ContributesAndroidInjector(modules = [NavHostModule::class])
    abstract fun mainActivityInjector(): MainActivity

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun provideSharedPreferences(
            app: Application
        ): SharedPreferences = PreferenceManager.getDefaultSharedPreferences(app)
    }
}