package com.doan.example.ui.screens.moviedetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.doan.example.R
import com.doan.example.databinding.FragmentMovieDetailBinding
import com.doan.example.extensions.provideNavArgs
import com.doan.example.extensions.provideViewModels
import com.doan.example.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailFragment : BaseFragment<FragmentMovieDetailBinding>() {

    private val viewModel: MovieDetailViewModel by provideViewModels()
    private val args: MovieDetailFragmentArgs by provideNavArgs()

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMovieDetailBinding
        get() = { inflater, container, attachToParent ->
            FragmentMovieDetailBinding.inflate(inflater, container, attachToParent)
        }

    override fun setupView() {
        // Hide navigation button on toolbar
        (requireActivity() as AppCompatActivity).supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(false)
            setHomeButtonEnabled(false)
        }
    }

    override fun initViewModel() {
        viewModel.initViewModel(args.uiModel)
    }

    override fun bindViewModel() {
        viewModel.id bindTo ::displayId
    }

    private fun displayId(id: Long?) {
        binding.tvSecondId.text = getString(R.string.second_id_title, id?.toString())
    }
}
