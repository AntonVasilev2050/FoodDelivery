package com.avv2050soft.fooddelivery.presentation.ui.menu

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.avv2050soft.fooddelivery.R
import com.avv2050soft.fooddelivery.databinding.FragmentMenuBinding
import com.avv2050soft.fooddelivery.domain.models.TopBanner
import com.avv2050soft.fooddelivery.domain.models.categories.Category
import com.avv2050soft.fooddelivery.domain.models.meals.Meal
import com.avv2050soft.fooddelivery.presentation.adapters.CategoriesAdapter
import com.avv2050soft.fooddelivery.presentation.adapters.MealsAdapter
import com.avv2050soft.fooddelivery.presentation.adapters.TopBannersAdapter
import com.avv2050soft.fooddelivery.presentation.utils.launchAndCollectIn
import com.avv2050soft.fooddelivery.presentation.utils.toastString
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MenuFragment : Fragment(R.layout.fragment_menu) {

    private val binding by viewBinding(FragmentMenuBinding::bind)
    private val viewModel by viewModels<MenuViewModel>()
    private val topBannerAdapter = TopBannersAdapter(
        onClickItem = { topBanner: TopBanner -> onTopBannerItemClick(topBanner) }
    )

    private fun onTopBannerItemClick(topBanner: TopBanner) {
        toastString("${topBanner.id} was clicked")
    }

    private val categoriesAdapter = CategoriesAdapter(
        onClickItem = { category: Category -> onCategoryItemClick(category) }
    )

    private fun onCategoryItemClick(category: Category) {
        toastString("${category.strCategory} was clicked")
        viewModel.viewModelScope.launch { viewModel.loadMeals(category.strCategory) }
    }

    private val mealsAdapter = MealsAdapter(
        onClickItem = { meal: Meal -> onMealItemClick(meal) }
    )

    private fun onMealItemClick(meal: Meal) {
        toastString("${meal.idMeal} was clicked")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.topBannerListStateFlow.launchAndCollectIn(viewLifecycleOwner) {
            viewModel.loadTopBanner()
            topBannerAdapter.submitList(it)
            binding.recyclerViewTopBanner.adapter = topBannerAdapter
        }
        viewModel.categoriesStateFlow.launchAndCollectIn(viewLifecycleOwner) {
            viewModel.loadCategories()
            categoriesAdapter.submitList(it)
            binding.recyclerViewCategories.adapter = categoriesAdapter
        }
        viewModel.mealsStateFlow.launchAndCollectIn(viewLifecycleOwner) {
            mealsAdapter.submitList(it)
            binding.recyclerViewMeals.adapter = mealsAdapter
        }
        viewModel.message.launchAndCollectIn(viewLifecycleOwner) {
            toastString(it)
        }
    }
}