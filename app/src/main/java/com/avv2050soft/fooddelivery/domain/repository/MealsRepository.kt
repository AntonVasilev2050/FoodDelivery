package com.avv2050soft.fooddelivery.domain.repository

import com.avv2050soft.fooddelivery.domain.models.categories.Categories
import com.avv2050soft.fooddelivery.domain.models.meals.Meals

interface MealsRepository {
    suspend fun getCategories(): Categories
    suspend fun getMeals(strCategory: String): Meals
}