package com.avv2050soft.fooddelivery.presentation.ui.menu

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.avv2050soft.fooddelivery.R
import com.avv2050soft.fooddelivery.data.objects.TopBanners
import com.avv2050soft.fooddelivery.domain.models.TopBanner
import com.avv2050soft.fooddelivery.domain.models.categories.Category
import com.avv2050soft.fooddelivery.domain.models.meals.Meal
import com.avv2050soft.fooddelivery.domain.repository.MealsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(
    private val repository: MealsRepository,
    private val context: Context
) : ViewModel() {

    private var topBannerList = listOf<TopBanner>()
    private val _topBannerListStateFlow = MutableStateFlow(topBannerList)
    val topBannerListStateFlow = _topBannerListStateFlow

    private var categories = listOf<Category>()
    private val _categoriesStateFlow = MutableStateFlow(categories)
    val categoriesStateFlow = _categoriesStateFlow

    private var meals = listOf<Meal>()
    private val _mealsStateFlow = MutableStateFlow(meals)
    val mealsStateFlow = _mealsStateFlow

    private val _message = Channel<String>()
    val message = _message.receiveAsFlow()

    init {
        viewModelScope.launch { loadMeals("Beef") }
    }

    suspend fun loadTopBanner() {
        // Just imitation of getting the topBannerList from an API
        viewModelScope.launch {
            runCatching {
                topBannerList = TopBanners.list
            }.onSuccess {
                _topBannerListStateFlow.value = topBannerList
            }.onFailure {
                _message.send(context.getString(R.string.an_error_occurred_while_getting_topbannerlist))
                Log.d("data_test", it.message.toString())
            }
        }
    }

    suspend fun loadCategories() {
        viewModelScope.launch {
            runCatching {
                categories = repository.getCategories().categories
            }.onSuccess {
                _categoriesStateFlow.value = categories
            }.onFailure {
                _message.send(context.getString(R.string.an_error_occurred_while_getting_categories))
                Log.d("data_test", it.message.toString())
            }
        }
    }

    suspend fun loadMeals(category: String){
        viewModelScope.launch {
            runCatching {
                meals = repository.getMeals(category).meals
            }.onSuccess {
                _mealsStateFlow.value = meals
            }.onFailure {
                _message.send(context.getString(R.string.an_error_occurred_while_getting_meal_list))
                Log.d("data_test", it.message.toString())
            }
        }
    }

}