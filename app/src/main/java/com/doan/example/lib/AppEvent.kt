package com.doan.example.lib

import com.doan.example.model.MovieUiModel

sealed class AppEvent {
    data class MovieList(val movies: List<MovieUiModel>) : AppEvent()
    data class Error(val error: Throwable) : AppEvent()
}