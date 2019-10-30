package com.nedarm.myapplication.meals

import com.nedarm.myapplication.R
import com.nedarm.myapplication.data.MealsResponse
import com.nedarm.myapplication.testutils.TestUtils
import com.nedarm.myapplication.ui.meals.MealsViewModel
import org.junit.Before
import org.junit.Test
import java.io.IOException
import java.util.*

class MealsViewModelTest {

    private lateinit var mealPlanViewModel: MealsViewModel

    @Before
    fun setUp() {
        mealPlanViewModel = MealsViewModel()
    }

    @Test
    fun loading() {
        val loadingObserver = mealPlanViewModel.loading().test()
        mealPlanViewModel.loadingUpdated().accept(true)
        mealPlanViewModel.loadingUpdated().accept(false)

        loadingObserver.assertValues(true, false)
    }

    @Test
    fun meals() {
        val response = TestUtils.loadJson("src/test/resources/mock/meals_response.json", MealsResponse::class.java)
        mealPlanViewModel.mealsUpdated().accept(response!!.meals)
        mealPlanViewModel.meals().test().assertValue(response.meals)
    }

    @Test
    fun error() {
        val errorObserver = mealPlanViewModel.error().test()
        mealPlanViewModel.onError().accept(IOException())
        mealPlanViewModel.mealsUpdated().accept(Collections.emptyList())

        //Assert that first an error was emitted, followed by success
        errorObserver.assertValues(R.string.api_error_repos, -1)
    }
}