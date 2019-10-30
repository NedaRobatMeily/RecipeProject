package com.nedarm.myapplication.model

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi


data class Recipe(val label : String, var image : String){
    companion object{
        fun jsonAdapter(moshi: Moshi) : JsonAdapter<Recipe>{
            return moshi.adapter(Recipe::class.java)
        }
    }
}