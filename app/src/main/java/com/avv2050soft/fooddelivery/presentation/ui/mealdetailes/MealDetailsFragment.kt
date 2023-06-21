package com.avv2050soft.fooddelivery.presentation.ui.mealdetailes

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.avv2050soft.fooddelivery.R
import com.avv2050soft.fooddelivery.databinding.FragmentMealDetailsBinding
import com.avv2050soft.fooddelivery.presentation.ui.menu.MEAL_NAME_KEY
import com.avv2050soft.fooddelivery.presentation.ui.menu.MEAL_URL_KEY
import com.avv2050soft.fooddelivery.presentation.utils.hideAppbarAndBottomView
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MealDetailsFragment : Fragment(R.layout.fragment_meal_details) {

    private val binding by viewBinding(FragmentMealDetailsBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(requireContext())
            .inflateTransition(R.transition.grid_transition)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        hideAppbarAndBottomView(requireActivity())
        val mealUrl = arguments?.getString(MEAL_URL_KEY)
        val mealName = arguments?.getString(MEAL_NAME_KEY)

        Glide.with(binding.imageViewMealDetailsImage.context)
            .load(mealUrl)
            .into(binding.imageViewMealDetailsImage)
        binding.textViewMealDetailsName.text = mealName
    }
}