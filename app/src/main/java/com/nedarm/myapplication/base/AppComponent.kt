package com.nedarm.myapplication.base

import com.nedarm.myapplication.data.MealsServiceModule
import com.nedarm.myapplication.networking.ServiceModule
import com.nedarm.myapplication.ui.UiModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ServiceModule::class,
        MealsServiceModule::class,
        UiModule::class]
)
interface AppComponent : AndroidInjector<BaseApplication> {
    @Component.Factory
    abstract class Factory : AndroidInjector.Factory<BaseApplication>
}