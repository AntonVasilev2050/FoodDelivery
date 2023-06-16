package com.avv2050soft.fooddelivery.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.avv2050soft.fooddelivery.databinding.ItemTopBannerBinding
import com.avv2050soft.fooddelivery.domain.models.TopBanner
import com.bumptech.glide.Glide

class TopBannersAdapter(
    private val onClickItem: (TopBanner) -> Unit
) : ListAdapter<TopBanner, TopBannersViewHolder>(DiffUtilTopBanners()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopBannersViewHolder {
        return TopBannersViewHolder(
            ItemTopBannerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: TopBannersViewHolder, position: Int) {
        val item = getItem(position)
        with(holder.binding){
            Glide.with(imageViewTopBanner.context)
                .load(item.pictureId)
                .into(imageViewTopBanner)
            root.setOnClickListener { onClickItem.invoke(item) }
        }
    }
}

class DiffUtilTopBanners : DiffUtil.ItemCallback<TopBanner>() {
    override fun areItemsTheSame(oldItem: TopBanner, newItem: TopBanner): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: TopBanner, newItem: TopBanner): Boolean =
        oldItem == newItem
}

class TopBannersViewHolder(val binding: ItemTopBannerBinding) : RecyclerView.ViewHolder(binding.root)