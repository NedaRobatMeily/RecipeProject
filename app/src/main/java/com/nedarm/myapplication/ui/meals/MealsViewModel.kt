package com.nedarm.myapplication.ui.meals

import com.jakewharton.rxrelay2.BehaviorRelay
import com.nedarm.myapplication.R
import com.nedarm.myapplication.model.Meal
import io.reactivex.Observable
import io.reactivex.functions.Consumer
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MealsViewModel @Inject internal constructor() {

    private val mealsRelay: BehaviorRelay<List<Meal>> = BehaviorRelay.create()
    private val errorRelay: BehaviorRelay<Int> = BehaviorRelay.create()
    private val loadingRelay: BehaviorRelay<Boolean> = BehaviorRelay.create()

    fun loading(): Observable<Boolean> {
        return loadingRelay
    }

    fun meals(): Observable<List<Meal>> {
        return mealsRelay
    }

    fun error(): Observable<Int> {
        return errorRelay
    }

    internal fun loadingUpdated(): Consumer<Boolean?> {
        return loadingRelay
    }

    internal fun mealsUpdated(): Consumer<List<Meal>?> {
        errorRelay.accept(-1)
        return mealsRelay
    }

    internal fun onError(): Consumer<Throwable> {
        return Consumer { throwable: Throwable ->
            Timber.e(throwable, "Error loadingRelay Repos")
            errorRelay.accept(R.string.api_error_repos)
        }
    }



}