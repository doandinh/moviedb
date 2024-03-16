package com.doan.example.ui.screens

import androidx.fragment.app.Fragment
import com.doan.example.R
import com.doan.example.model.MovieUiModel
import com.doan.example.ui.base.*
import com.doan.example.ui.screens.home.HomeFragmentDirections.Companion.actionHomeFragmentToSecondFragment
import javax.inject.Inject

interface MainNavigator : BaseNavigator

class MainNavigatorImpl @Inject constructor(
    fragment: Fragment
) : BaseNavigatorImpl(fragment), MainNavigator {

    override val navHostFragmentId = R.id.fcvMainNavHostFragment

    override fun navigate(event: NavigationEvent) {
        when (event) {
            is NavigationEvent.Second -> navigateToSecond(event.movieUiModel)
        }
    }

    private fun navigateToSecond(movieUiModel: MovieUiModel) {
        val navController = findNavController()
        when (navController?.currentDestination?.id) {
            R.id.homeFragment -> navController.navigate(actionHomeFragmentToSecondFragment(movieUiModel))
            else -> unsupportedNavigation()
        }
    }
}
