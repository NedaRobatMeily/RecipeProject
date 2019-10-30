package com.nedarm.myapplication.data

import com.nedarm.myapplication.model.Meal
import com.squareup.moshi.Json
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi


data class MealsResponse(@Json(name = "hits") var meals: List<Meal>) {

    fun jsonAdapter(moshi: Moshi) : JsonAdapter<MealsResponse> {
        return moshi.adapter(MealsResponse::class.java)
    }
}