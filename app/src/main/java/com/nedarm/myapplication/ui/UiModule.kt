package com.nedarm.myapplication.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nedarm.myapplication.di.AppViewModelFactory
import com.nedarm.myapplication.ui.meals.MealsModule
import dagger.Module
import dagger.Provides
import javax.inject.Provider

@Module(
    includes = [
        MealsModule::class
    ]
)
class UiModule {

    @Provides
    fun provideViewModelFactory(
        providers: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
    ): ViewModelProvider.Factory =
        AppViewModelFactory(providers)
}