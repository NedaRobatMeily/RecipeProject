package com.nedarm.myapplication.base

import com.nedarm.myapplication.data.MealsModule
import com.nedarm.myapplication.networking.ServiceModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AppModule::class,
        AndroidSupportInjectionModule::class,
        ServiceModule::class,
        MealsModule::class]
)
interface AppComponent : AndroidInjector<BaseApplication> {
    @Component.Factory
    abstract class Factory : AndroidInjector.Factory<BaseApplication>
}