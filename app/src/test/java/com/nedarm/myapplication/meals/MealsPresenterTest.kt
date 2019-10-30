package com.nedarm.myapplication.meals

import com.nedarm.myapplication.data.MealsRequester
import com.nedarm.myapplication.data.MealsResponse
import com.nedarm.myapplication.model.Meal
import com.nedarm.myapplication.testutils.TestUtils
import com.nedarm.myapplication.ui.meals.MealsViewModel
import io.reactivex.Single
import io.reactivex.functions.Consumer
import org.junit.Before
import org.junit.Test
import org.mockito.InOrder
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.io.IOException

class MealsPresenterTest {

    @Mock
    internal lateinit var repoRequester: MealsRequester
    @Mock
    internal lateinit var viewModel: MealsViewModel
    @Mock
    internal lateinit var onErrorConsumer: Consumer<Throwable>
    @Mock
    internal lateinit var onSuccessConsumer: Consumer<List<Meal>>

    @Mock
    internal lateinit var loadingConsumer: Consumer<Boolean>

    private lateinit var presenter: MealsPresenter

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        Mockito.`when`<Any?>(viewModel.loadingUpdated())
            .thenReturn(loadingConsumer)
        Mockito.`when`<Any?>(viewModel.onError()).thenReturn(onErrorConsumer)
        Mockito.`when`<Any?>(viewModel.mealsUpdated())
            .thenReturn(onSuccessConsumer)
    }

    @Test
    @Throws(Exception::class)
    fun reposLoaded() {
        val repos: List<Meal> = setUpSuccess()
        initializePresenter()

        //Verify that getMeals is called after setup
        Mockito.verify<MealsRequester?>(repoRequester)!!.getMeals()

        //Verify that onSuccess is called
        Mockito.verify<Consumer<List<Meal>>?>(
            onSuccessConsumer
        )!!.accept(repos)

        //Verify that there were no interactions with the onErrorConsumer
        Mockito.verifyZeroInteractions(onErrorConsumer)
    }

    @Test
    @Throws(Exception::class)
    fun reposLoadedError() {
        val error = setUpError()
        initializePresenter()
        Mockito.verify(
            onErrorConsumer
        )!!.accept(error)

        Mockito.verifyZeroInteractions(onSuccessConsumer)
    }

    @Test
    @Throws(Exception::class)
    fun loadingSuccess() {
        setUpSuccess()
        initializePresenter()

        val inOrder: InOrder = Mockito.inOrder(loadingConsumer)
        inOrder.verify(loadingConsumer)
            .accept(true)

        inOrder.verify(loadingConsumer)
            .accept(false)
    }

    @Test
    @Throws(Exception::class)
    fun loadingError() {
        setUpError()
        initializePresenter()
        val inOrder: InOrder = Mockito.inOrder(loadingConsumer)
        inOrder.verify(loadingConsumer)
            .accept(true)
        inOrder.verify(loadingConsumer)
            .accept(false)
    }

    @Test
    @Throws(Exception::class)
    fun onMealClicked() {
        //TODO

    }

    private fun setUpSuccess(): List<Meal> {
        val response: MealsResponse? = TestUtils.loadJson(
            "src/test/resources/mock/meals_response.json",
            MealsResponse::class.java
        )
        val repos: List<Meal> = response!!.meals
        Mockito.`when`<Any>(repoRequester.getMeals())
            .thenReturn(Single.just<List<Meal>>(repos))
        return repos
    }

    private fun setUpError(): Throwable {
        val error: Throwable = IOException()
        Mockito.`when`<Any>(repoRequester.getMeals())
            .thenReturn(Single.error<Any?>(error))
        return error
    }

    private fun initializePresenter() {
        presenter = MealsPresenter(viewModel, repoRequester)
    }
}