package com.nedarm.myapplication.meals

import androidx.recyclerview.widget.DiffUtil
import com.nedarm.myapplication.model.Meal

class MealDiffCallback(
    private val oldList: List<Meal>,
    private val newList: List<Meal>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int
    ): Boolean {
        return oldList[oldItemPosition].recipe.label == newList[newItemPosition].recipe.label
    }

    override fun areContentsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int
    ): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}