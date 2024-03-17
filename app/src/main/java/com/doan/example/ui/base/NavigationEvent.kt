package com.doan.example.ui.base


sealed class NavigationEvent {
    data class MovieDetail(val movieId: Long) : NavigationEvent()
}
