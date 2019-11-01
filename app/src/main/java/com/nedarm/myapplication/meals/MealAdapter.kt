package com.nedarm.myapplication.meals

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.nedarm.myapplication.R
import com.nedarm.myapplication.model.Meal
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.view_meal_list_item.*
import java.util.*

internal class MealAdapter(private val listener: MealClickedAdapter) :
    RecyclerView.Adapter<MealAdapter.MealViewHolder>() {
    private val data: MutableList<Meal> =
        ArrayList()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MealViewHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_meal_list_item, parent, false)
        return MealViewHolder(itemView, listener)
    }

    override fun onBindViewHolder(
        holder: MealViewHolder,
        position: Int
    ) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    fun setData(meals: List<Meal>) {
        val diffResult = DiffUtil.calculateDiff(MealDiffCallback(data, meals))
        data.clear()
        data.addAll(meals)
        diffResult.dispatchUpdatesTo(this)
    }

    internal class MealViewHolder(
        override val containerView: View,
        listener: MealClickedAdapter
    ) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {

        private var meal: Meal? = null
        fun bind(meal: Meal) {
            this.meal = meal
            tv_meal_name.text = meal.recipe.label
        }

        init {
            itemView.setOnClickListener {
                if (meal != null) {
                    listener.onMealClicked(meal!!)
                }
            }
        }
    }

    internal interface MealClickedAdapter {
        fun onMealClicked(meal: Meal)
    }

    init {
        setHasStableIds(true)
    }
}