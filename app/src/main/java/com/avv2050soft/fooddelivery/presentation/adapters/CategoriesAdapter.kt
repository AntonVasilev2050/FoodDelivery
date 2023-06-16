package com.avv2050soft.fooddelivery.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.avv2050soft.fooddelivery.databinding.ItemCategoryBinding
import com.avv2050soft.fooddelivery.domain.models.categories.Category

class CategoriesAdapter(
    private val onClickItem: (Category) -> Unit
) : ListAdapter<Category, CategoriesViewHolder>(DiffUtilCategories()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        return CategoriesViewHolder(
            ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.textViewCategoryName.text = item.strCategory
        holder.binding.root.setOnClickListener { onClickItem.invoke(item) }
    }
}

class DiffUtilCategories : DiffUtil.ItemCallback<Category>() {
    override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean =
        oldItem.idCategory == newItem.idCategory

    override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean =
        oldItem == newItem
}

class CategoriesViewHolder(val binding: ItemCategoryBinding) :
    RecyclerView.ViewHolder(binding.root)