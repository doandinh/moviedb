package com.doan.example.ui.screens.moviedetail

import androidx.lifecycle.viewModelScope
import com.doan.example.domain.usecases.GetMovieDetailUseCase
import com.doan.example.model.MovieDetailUiModel
import com.doan.example.model.toUiModel
import com.doan.example.ui.base.BaseViewModel
import com.doan.example.util.DispatchersProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val dispatchersProvider: DispatchersProvider,
    private val getMovieDetailUseCase: GetMovieDetailUseCase,
) : BaseViewModel() {

    init {
        showLoading()
    }

    private val _movieDetailUiModel = MutableStateFlow<MovieDetailUiModel?>(null)
    val movieDetailUiModel = _movieDetailUiModel.asStateFlow()

    fun getMovieDetail(movieId: Long) = launch {
        getMovieDetailUseCase(movieId)
            .injectLoading()
            .onEach { result ->
                _movieDetailUiModel.emit(result.toUiModel())
            }
            .flowOn(dispatchersProvider.io)
            .catch { e -> _error.emit(e) }
            .launchIn(viewModelScope)
    }

    fun hideLoadingIndicator() {
        super.hideLoading()
    }
}
