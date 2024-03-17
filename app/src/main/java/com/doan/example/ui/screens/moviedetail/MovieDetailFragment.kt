package com.doan.example.ui.screens.moviedetail

import android.graphics.Color
import android.graphics.Typeface
import android.text.SpannableString
import android.text.Spanned.SPAN_EXCLUSIVE_INCLUSIVE
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import coil.load
import coil.transform.RoundedCornersTransformation
import com.doan.example.R
import com.doan.example.databinding.FragmentMovieDetailBinding
import com.doan.example.extensions.*
import com.doan.example.lib.*
import com.doan.example.model.MovieDetailUiModel
import com.doan.example.ui.base.BaseFragment
import com.doan.example.ui.screens.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieDetailFragment : BaseFragment<FragmentMovieDetailBinding>() {

    private val viewModel: MovieDetailViewModel by provideViewModels()
    private val args: MovieDetailFragmentArgs by provideNavArgs()

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMovieDetailBinding
        get() = { inflater, container, attachToParent ->
            FragmentMovieDetailBinding.inflate(inflater, container, attachToParent)
        }

    override fun setupView() {
        subScribeEventBus()
        // Hide navigation button on toolbar
        (requireActivity() as AppCompatActivity).supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(false)
            setHomeButtonEnabled(false)
        }
        isLoading(true)
        (activity as? MainActivity)?.getMovieDetail(args.movieId)
    }

    private fun isLoading(isLoading: IsLoading) {
        binding?.run {
            pbMovieDetail.visibleOrGone(isLoading)
        }
    }

    override fun initViewModel() {
//        viewModel.getMovieDetail(args.movieId)
    }

    override fun bindViewModel() {
        viewModel.isLoading bindTo ::isLoading
        viewModel.movieDetailUiModel bindTo ::displayMovieDetail
    }

    private fun subScribeEventBus() {
        lifecycleScope.launch {
            AppEventBus.subscribe<AppEvent> { appEvent ->
                isLoading(false)
                when (appEvent) {
                    is AppEvent.MovieDetail -> {
                        displayMovieDetail(appEvent.movie)
                    }

                    is AppEvent.Error -> {
                        displayError(appEvent.error)
                    }

                    else -> {}
                }
            }
        }
    }

    private fun displayMovieDetail(movieDetail: MovieDetailUiModel?) {
        movieDetail?.run {
            (requireActivity() as AppCompatActivity).supportActionBar?.apply {
                title = movieDetail.title
            }
            binding?.run {
                tvName.text = movieDetail.originalTitle
                tvOverview.text = movieDetail.overview
                ivPoster.load(movieDetail.posterPath) {
                    crossfade(true)
                    placeholder(android.R.drawable.ic_media_play)
                    transformations(RoundedCornersTransformation())
                }
                val strPopularity = getString(R.string.movie_detail_popularity)
                val fullStrPopularity =
                    strPopularity + " " + String.format("%.2f", movieDetail.popularity)
                val spPopularity = SpannableString(fullStrPopularity)
                spPopularity.setSpan(
                    ForegroundColorSpan(Color.GREEN),
                    strPopularity.length, // start
                    fullStrPopularity.length, // end
                    SPAN_EXCLUSIVE_INCLUSIVE
                )
                spPopularity.setSpan(
                    StyleSpan(Typeface.BOLD_ITALIC),
                    strPopularity.length, // start
                    fullStrPopularity.length, // end
                    SPAN_EXCLUSIVE_INCLUSIVE
                )
                tvPopularity.text = spPopularity

                val strStatus = getString(R.string.movie_detail_status)
                val fullStrStatus = strStatus + " " + movieDetail.status
                val spStatus = SpannableString(fullStrStatus)
                spStatus.setSpan(
                    ForegroundColorSpan(Color.RED),
                    strStatus.length, // start
                    fullStrStatus.length, // end
                    SPAN_EXCLUSIVE_INCLUSIVE
                )
                spStatus.setSpan(
                    StyleSpan(Typeface.BOLD),
                    strStatus.length, // start
                    fullStrStatus.length, // end
                    SPAN_EXCLUSIVE_INCLUSIVE
                )
                tvStatus.text = spStatus
            }
        }
    }
}
