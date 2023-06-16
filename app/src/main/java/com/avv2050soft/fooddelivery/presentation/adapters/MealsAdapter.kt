package com.avv2050soft.fooddelivery.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.avv2050soft.fooddelivery.databinding.ItemMealBinding
import com.avv2050soft.fooddelivery.domain.models.meals.Meal
import com.avv2050soft.fooddelivery.presentation.utils.loremIpsum
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class MealsAdapter(
    private val onClickItem: (Meal) -> Unit
) : ListAdapter<Meal, MealsViewHolder>(DiffUtilMeals()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealsViewHolder {
        return MealsViewHolder(
            ItemMealBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: MealsViewHolder, position: Int) {
        val item = getItem(position)
        with(holder.binding) {
            Glide.with(imageViewMealPicture.context)
                .load(item.strMealThumb)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageViewMealPicture)
            textViewMealName.text = item.strMeal
//            textViewMealDescription.text = String.loremIpsum(1)
            textViewMealDescription.text =
                buildString {
                    append("Lorem ipsum dolor sit amet, consectetur adipiscing elit, ")
                    append("sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. ")
                }
            textViewMealPrice.text = buildString {
                append("from ")
                append((item.idMeal.toInt() / 100).toString())
                append(" Rub")
            }
            root.setOnClickListener { onClickItem.invoke(item) }
        }
    }
}

class DiffUtilMeals : DiffUtil.ItemCallback<Meal>() {
    override fun areItemsTheSame(oldItem: Meal, newItem: Meal): Boolean =
        oldItem.idMeal == newItem.idMeal

    override fun areContentsTheSame(oldItem: Meal, newItem: Meal): Boolean =
        oldItem == newItem
}

class MealsViewHolder(val binding: ItemMealBinding) : RecyclerView.ViewHolder(binding.root)