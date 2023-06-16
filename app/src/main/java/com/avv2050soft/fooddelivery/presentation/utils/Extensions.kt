package com.avv2050soft.fooddelivery.presentation.utils

import android.app.Activity
import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.avv2050soft.fooddelivery.R
import com.github.javafaker.Faker
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.util.Locale

fun hideAppbarAndBottomView(requireActivity: Activity) {
    requireActivity.findViewById<Toolbar>(R.id.toolbar).visibility = View.GONE
    val bottomNavigationView = requireActivity.findViewById<BottomNavigationView>(R.id.nav_view)
    bottomNavigationView.visibility = View.GONE
}

fun showAppbarAndBottomView(requireActivity: Activity) {
    requireActivity.findViewById<Toolbar>(R.id.toolbar).visibility = View.VISIBLE
    val bottomNavigationView = requireActivity.findViewById<BottomNavigationView>(R.id.nav_view)
    bottomNavigationView.visibility = View.VISIBLE
}

fun Fragment.toast(@StringRes stringRes: Int) {
    Toast.makeText(requireContext(), stringRes, Toast.LENGTH_SHORT).show()
}

fun Fragment.toastString(msg: String?) {
    Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
}

fun Activity.toastString(msg: String?) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

fun String.Companion.loremIpsum(paragraphs: Int): String {
    val faker = Faker(Locale.getDefault())
    val loremIpsumText = StringBuilder()

    repeat(paragraphs) {
        val paragraph = faker.lorem().paragraph()
        loremIpsumText.append(paragraph)
        loremIpsumText.append("\n\n") // Добавляем пустую строку между абзацами
    }

    return loremIpsumText.toString()
}

inline fun <T> Flow<T>.launchAndCollectIn(
    owner: LifecycleOwner,
    minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
    crossinline action: suspend CoroutineScope.(T) -> Unit
) = owner.lifecycleScope.launch {
    owner.repeatOnLifecycle(minActiveState) {
        collect {
            action(it)
        }
    }
}