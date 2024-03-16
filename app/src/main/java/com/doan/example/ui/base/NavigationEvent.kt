package com.doan.example.ui.base

import com.doan.example.model.UiModel

sealed class NavigationEvent {
    data class Second(val uiModel: UiModel) : NavigationEvent()
}
