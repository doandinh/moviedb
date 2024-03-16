package com.doan.example.ui.screens.home

import androidx.lifecycle.viewModelScope
import com.doan.example.domain.usecases.GetMoviesUseCase
import com.doan.example.model.MovieUiModel
import com.doan.example.model.toUiModel
import com.doan.example.ui.base.BaseViewModel
import com.doan.example.ui.base.NavigationEvent
import com.doan.example.util.DispatchersProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val dispatchersProvider: DispatchersProvider,
    private val getMoviesUseCase: GetMoviesUseCase,
) : BaseViewModel() {

    private val _Movie_uiModels = MutableStateFlow<List<MovieUiModel>>(emptyList())
    val movieUiModels: StateFlow<List<MovieUiModel>> = _Movie_uiModels

    fun navigateToSecond(movieUiModel: MovieUiModel) {
        launch { _navigator.emit(NavigationEvent.Second(movieUiModel)) }
    }

    fun getMovies() {
        getMoviesUseCase()
            .injectLoading()
            .onEach { result ->
                val uiModels = result.results.map { it.toUiModel() }
                _Movie_uiModels.emit(uiModels)
            }
            .flowOn(dispatchersProvider.io)
            .catch { e -> _error.emit(e) }
            .launchIn(viewModelScope)
    }
}
