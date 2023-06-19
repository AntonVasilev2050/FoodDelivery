package com.avv2050soft.fooddelivery.data.api

//https://www.themealdb.com/api.php

import com.avv2050soft.fooddelivery.domain.models.categories.Categories
import com.avv2050soft.fooddelivery.domain.models.meals.Meals
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface MealsApi {

    @GET("categories.php")
    suspend fun getCategories(
    ): Categories

    @GET("filter.php")
    suspend fun getMeals(
        @Query("c") strCategory: String
    ): Meals

    companion object {
        private const val BASE_URL = "https://www.themealdb.com/api/json/v1/1/"

        fun create(): MealsApi {
            val logger =
                HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MealsApi::class.java)
        }
    }
}