package com.doan.example.ui.base

import com.doan.example.model.MovieUiModel

sealed class NavigationEvent {
    data class Second(val movieUiModel: MovieUiModel) : NavigationEvent()
}
