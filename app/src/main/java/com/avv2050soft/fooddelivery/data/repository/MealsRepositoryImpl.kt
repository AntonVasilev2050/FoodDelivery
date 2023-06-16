package com.avv2050soft.fooddelivery.data.repository

import com.avv2050soft.fooddelivery.data.api.MealsApi
import com.avv2050soft.fooddelivery.domain.models.categories.Categories
import com.avv2050soft.fooddelivery.domain.models.meals.Meals
import com.avv2050soft.fooddelivery.domain.repository.MealsRepository

class MealsRepositoryImpl : MealsRepository {
    override suspend fun getCategories(): Categories {
        return MealsApi.create().getCategories()
    }

    override suspend fun getMeals(strCategory: String): Meals {
        return MealsApi.create().getMeals(strCategory)
    }
}