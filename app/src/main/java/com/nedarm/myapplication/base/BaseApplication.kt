package com.nedarm.myapplication.base

import com.nedarm.myapplication.BuildConfig
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import timber.log.Timber

open class BaseApplication : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out BaseApplication> =
        DaggerAppComponent.factory().create(this)

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

}