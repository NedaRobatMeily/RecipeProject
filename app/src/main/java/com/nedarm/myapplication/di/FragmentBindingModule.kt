package com.nedarm.myapplication.di

import androidx.fragment.app.Fragment
import com.nedarm.myapplication.ui.meals.MealsFragment
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class FragmentBindingModule {

    @Binds
    @IntoMap
    @FragmentKey(MealsFragment::class)
    abstract fun bindMainFragment(mainFragment: MealsFragment): Fragment
}