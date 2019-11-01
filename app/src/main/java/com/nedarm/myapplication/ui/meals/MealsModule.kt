package com.nedarm.myapplication.ui.meals

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.nedarm.myapplication.data.MealsRequester
import com.nedarm.myapplication.di.ViewModelKey
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module(
    includes = [
        MealsModule.ProvideViewModel::class
    ]
)
abstract class MealsModule {

    @ContributesAndroidInjector(
        modules = [
            InjectViewModel::class
        ]
    )
    abstract fun bind(): MealsFragment

    @Module
    class ProvideViewModel {

        @Provides
        @IntoMap
        @ViewModelKey(MealsViewModel::class)
        fun provideListNotesViewModel(mealsRequester: MealsRequester): ViewModel =
            MealsViewModel(mealsRequester)
    }

    @Module
    class InjectViewModel {

        @Provides
        fun provideListNotesViewModel(
            factory: ViewModelProvider.Factory,
            target: MealsFragment
        ): MealsViewModel =
            ViewModelProviders.of(target, factory).get(MealsViewModel::class.java)
    }

}