package com.doan.example.ui.screens.second

import com.doan.example.model.MovieUiModel
import com.doan.example.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SecondViewModel @Inject constructor() : BaseViewModel() {

    private val _id = MutableStateFlow<Long?>(null)
    val id: StateFlow<Long?>
        get() = _id

    fun initViewModel(movieUiModel: MovieUiModel) = launch { _id.emit(movieUiModel.id) }
}
