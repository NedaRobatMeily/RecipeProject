package com.nedarm.myapplication.data

import io.reactivex.Single
import retrofit2.http.GET


interface MealsService{
    @GET("search?q=vegeterian&app_id=%203f79644f&app_key=%204da2f4008214ffb37513d82637666379")
    fun getMeals() : Single<MealsResponse>
}