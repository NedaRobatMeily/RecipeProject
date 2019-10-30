package com.nedarm.myapplication.data

import com.nedarm.myapplication.model.Meal
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MealsRequester @Inject internal constructor(var service: MealsService) {

    fun getMeals() : Single<List<Meal>>{
        return service.getMeals().map(MealsResponse::meals)
            .subscribeOn(Schedulers.io())
    }
}