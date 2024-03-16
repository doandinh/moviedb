package com.doan.example.model

import android.os.Parcelable
import com.doan.example.domain.models.Movies
import kotlinx.parcelize.Parcelize

@Parcelize
data class UiModel(
    val id: String
) : Parcelable

fun Movies.Movie.toUiModel() = UiModel(id = id.toString())
