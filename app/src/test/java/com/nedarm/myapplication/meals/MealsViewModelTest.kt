package com.nedarm.myapplication.meals

import com.nedarm.myapplication.R
import com.nedarm.myapplication.data.MealsRequester
import com.nedarm.myapplication.data.MealsResponse
import com.nedarm.myapplication.testutils.TestUtils
import com.nedarm.myapplication.ui.meals.MealsViewModel
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import java.io.IOException
import java.util.*


class MealsViewModelTest {

    private lateinit var mealsViewModel: MealsViewModel

    @Mock
    internal lateinit var mealsRequester: MealsRequester

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        mealsViewModel = MealsViewModel(mealsRequester = mealsRequester)
    }

    @Test
    fun loading() {
        val loadingObserver = mealsViewModel.loading().test()
        mealsViewModel.loadingUpdated().accept(true)
        mealsViewModel.loadingUpdated().accept(false)

        loadingObserver.assertValues(true, false)
    }

    @Test
    fun meals() {
        val response = TestUtils.loadJson("src/test/resources/mock/meals_response.json", MealsResponse::class.java)
        mealsViewModel.mealsUpdated().accept(response!!.meals)
        mealsViewModel.meals().test().assertValue(response.meals)
    }

    @Test
    fun error() {
        val errorObserver = mealsViewModel.error().test()
        mealsViewModel.onError().accept(IOException())
        mealsViewModel.mealsUpdated().accept(Collections.emptyList())

        //Assert that first an error was emitted, followed by success
        errorObserver.assertValues(R.string.api_error_meals, -1)
    }
}