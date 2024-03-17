package com.doan.example.ui.screens.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.doan.example.databinding.FragmentHomeBinding
import com.doan.example.extensions.provideViewModels
import com.doan.example.extensions.visibleOrGone
import com.doan.example.lib.*
import com.doan.example.model.MovieUiModel
import com.doan.example.ui.base.BaseFragment
import com.doan.example.ui.screens.MainNavigator
import com.doan.example.ui.screens.home.adapter.MoviesAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    @Inject
    lateinit var navigator: MainNavigator

    private val viewModel: HomeViewModel by provideViewModels()
    private val moviesAdapter: MoviesAdapter by lazy { MoviesAdapter(context = requireContext()) }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding
        get() = { inflater, container, attachToParent ->
            FragmentHomeBinding.inflate(inflater, container, attachToParent)
        }

    override fun setupView() {
        subScribeEventBus()
        binding?.gvHome?.apply {
            moviesAdapter.onItemClicked = { movieId -> viewModel.navigateToDetail(movieId) }
            adapter = moviesAdapter
        }
//        viewModel.getMovies()
    }

    private fun subScribeEventBus() {
        lifecycleScope.launch {
            AppEventBus.subscribe<AppEvent> { appEvent ->
                when (appEvent) {
                    is AppEvent.MovieList -> {
                        viewModel.hideLoadingIndicator()
                        displayMoviesUi(appEvent.movies)
                    }
                    is AppEvent.Error -> {
                        viewModel.hideLoadingIndicator()
                        displayError(appEvent.error)
                    }

                    else -> {}
                }
            }
        }
    }

    override fun bindViewModel() {
        viewModel.movieUiModels bindTo ::displayMoviesUi
        viewModel.error bindTo ::displayError
        viewModel.navigator bindTo navigator::navigate
        viewModel.isLoading bindTo ::isLoading
    }


    private fun isLoading(isLoading: IsLoading) {
        binding?.run {
            pbHome.visibleOrGone(isLoading)
        }
    }

    private fun displayMoviesUi(movieUiModels: List<MovieUiModel>) {
        moviesAdapter.addAll(movieUiModels)
    }
}
