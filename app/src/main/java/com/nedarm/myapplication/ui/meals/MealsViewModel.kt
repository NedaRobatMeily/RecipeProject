package com.nedarm.myapplication.ui.meals

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import com.jakewharton.rxrelay2.BehaviorRelay
import com.nedarm.myapplication.R
import com.nedarm.myapplication.data.MealsRequester
import com.nedarm.myapplication.meals.MealAdapter
import com.nedarm.myapplication.model.Meal
import io.reactivex.Observable
import io.reactivex.functions.Consumer
import timber.log.Timber
import javax.inject.Inject

class MealsViewModel @Inject internal constructor(var mealsRequester: MealsRequester) : ViewModel(),
    MealAdapter.MealClickedAdapter {

    private val mealsRelay: BehaviorRelay<List<Meal>> = BehaviorRelay.create()
    private val errorRelay: BehaviorRelay<Int> = BehaviorRelay.create()
    private val loadingRelay: BehaviorRelay<Boolean> = BehaviorRelay.create()
    private val loaded: BehaviorRelay<Boolean> = BehaviorRelay.create()


    @SuppressLint("CheckResult")
    fun loadMeals() {
        if (loaded.value != null && loaded.value) {
            return
        }
        mealsRequester.getMeals().doOnSubscribe {
            loadingUpdated().accept(true)
        }.doOnEvent { _, _ -> loadingUpdated().accept(false) }
            .subscribe(mealsUpdated(), onError())

    }

    override fun onMealClicked(meal: Meal) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

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
        loaded.accept(true)
        return mealsRelay
    }

    internal fun onError(): Consumer<Throwable> {
        return Consumer { throwable: Throwable ->
            Timber.e(throwable, "Error loadingRelay Meals")
            errorRelay.accept(R.string.api_error_meals)
        }
    }

}