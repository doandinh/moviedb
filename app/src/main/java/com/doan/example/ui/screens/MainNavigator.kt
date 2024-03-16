package com.doan.example.ui.screens

import androidx.fragment.app.Fragment
import com.doan.example.R
import com.doan.example.model.UiModel
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
            is NavigationEvent.Second -> navigateToSecond(event.uiModel)
        }
    }

    private fun navigateToSecond(uiModel: UiModel) {
        val navController = findNavController()
        when (navController?.currentDestination?.id) {
            R.id.homeFragment -> navController.navigate(actionHomeFragmentToSecondFragment(uiModel))
            else -> unsupportedNavigation()
        }
    }
}
