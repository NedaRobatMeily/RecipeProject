package com.nedarm.myapplication.model

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi


data class Meal(var recipe: Recipe){

    companion object{
        fun jsonAdapter(moshi: Moshi) : JsonAdapter<Meal>{
            return moshi.adapter(Meal::class.java)
        }
    }
}