package com.nedarm.myapplication.ui.meals

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.nedarm.myapplication.R
import com.nedarm.myapplication.base.BaseFragment
import com.nedarm.myapplication.meals.MealAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.fragment_meal_plan.view.*
import timber.log.Timber
import javax.inject.Inject

class MealsFragment @Inject constructor() : BaseFragment() {
    @Inject
    lateinit var mealsViewModel: MealsViewModel

    override fun onViewBound(view: View) {
        view.meal_list.layoutManager = LinearLayoutManager(view.context)
        view.meal_list.adapter = MealAdapter(mealsViewModel)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mealsViewModel.loadMeals()
    }

    override fun layoutRes(): Int {
        return R.layout.fragment_meal_plan
    }

    override fun subscriptions(): Array<Disposable?> {
        val d1 = mealsViewModel.loading().observeOn(AndroidSchedulers.mainThread())
            .subscribe { loading ->
                if (loading == true) {
                    view?.loading_indicator?.visibility = View.VISIBLE
                    view?.meal_list?.visibility = View.GONE
                    view?.tv_error?.visibility = View.GONE
                } else {
                    view?.loading_indicator?.visibility = View.GONE
                    view?.meal_list?.visibility = View.VISIBLE
                }
            }


        val d2 = mealsViewModel.meals().observeOn(AndroidSchedulers.mainThread())
            .subscribe { meals ->
                Timber.d(meals.toString())
                (view?.meal_list?.adapter as MealAdapter).setData(meals)
            }

        val d3 = mealsViewModel.error().observeOn(AndroidSchedulers.mainThread())
            .subscribe { errorRes ->
                if (errorRes == -1) {
                    view?.tv_error?.text = null
                    view?.tv_error?.visibility = View.GONE
                } else {
                    view?.tv_error?.visibility = View.VISIBLE
                    view?.meal_list?.visibility = View.GONE
                    view?.tv_error?.text = resources.getString(errorRes)
                }
            }

        return arrayOf(d1, d2, d3)
    }
}