package com.nedarm.myapplication.meals

import android.annotation.SuppressLint
import com.nedarm.myapplication.data.MealsRequester
import com.nedarm.myapplication.model.Meal
import com.nedarm.myapplication.ui.meals.MealsViewModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MealsPresenter @Inject constructor(
    var viewModel: MealsViewModel,
    var mealsRequester: MealsRequester
) : MealAdapter.MealClickedAdapter {

    init {
        loadMeals()
    }

    @SuppressLint("CheckResult")
    fun loadMeals() {
        mealsRequester.getMeals().doOnSubscribe {
            viewModel.loadingUpdated().accept(true)
        }.doOnEvent { _, _ -> viewModel.loadingUpdated().accept(false) }
            .subscribe(viewModel.mealsUpdated(), viewModel.onError())

    }

    override fun onMealClicked(meal: Meal) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}