package com.doan.example.ui.screens.home

import android.view.LayoutInflater
import android.view.ViewGroup
import co.nimblehq.common.extensions.visibleOrGone
import com.doan.example.databinding.FragmentHomeBinding
import com.doan.example.extensions.provideViewModels
import com.doan.example.lib.IsLoading
import com.doan.example.model.UiModel
import com.doan.example.ui.base.BaseFragment
import com.doan.example.ui.screens.MainNavigator
import com.doan.example.ui.screens.home.adapter.MoviesAdapter
import dagger.hilt.android.AndroidEntryPoint
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
        binding.gvHome.apply {
            moviesAdapter.onItemClicked = { uiModel -> viewModel.navigateToSecond(uiModel) }
            adapter = moviesAdapter
        }

        viewModel.getMovies()
    }

    override fun bindViewModel() {
        viewModel.uiModels bindTo ::displayUiModels
        viewModel.error bindTo ::displayError
        viewModel.navigator bindTo navigator::navigate
        viewModel.isLoading bindTo ::isLoading
    }


    private fun isLoading(isLoading: IsLoading) {
        binding.pbHome.visibleOrGone(isLoading)
    }

    private fun displayUiModels(uiModels: List<UiModel>) {
        moviesAdapter.addAll(uiModels)
    }
}