package com.doan.example.ui.screens

import androidx.fragment.app.Fragment
import com.doan.example.R
import com.doan.example.ui.base.*
import com.doan.example.ui.screens.home.HomeFragmentDirections.Companion.actionHomeFragmentToMovieDetailFragment
import javax.inject.Inject

interface MainNavigator : BaseNavigator

class MainNavigatorImpl @Inject constructor(
    fragment: Fragment
) : BaseNavigatorImpl(fragment), MainNavigator {

    override val navHostFragmentId = R.id.fcvMainNavHostFragment

    override fun navigate(event: NavigationEvent) {
        when (event) {
            is NavigationEvent.MovieDetail -> navigateToMovieDetail(event.movieId)
        }
    }

    private fun navigateToMovieDetail(movieId: Long) {
        val navController = findNavController()
        when (navController?.currentDestination?.id) {
            R.id.homeFragment -> navController.navigate(actionHomeFragmentToMovieDetailFragment(movieId))
            else -> unsupportedNavigation()
        }
    }
}
