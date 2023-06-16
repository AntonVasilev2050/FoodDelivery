package com.avv2050soft.fooddelivery.domain.models.categories


import com.google.gson.annotations.SerializedName

data class Categories(
    @SerializedName("categories")
    val categories: List<Category>
)