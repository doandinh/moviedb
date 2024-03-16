package com.doan.example.ui.base


sealed class NavigationEvent {
    data class Second(val movieId: Long) : NavigationEvent()
}
