package com.doan.example.model

import android.os.Parcelable
import com.doan.example.BuildConfig
import com.doan.example.domain.models.Movies
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieUiModel(
    val id: Long,
    val posterPath: String,
    val title: String,
) : Parcelable

fun Movies.Movie.toUiModel() = MovieUiModel(
    id = id,
    title = title,
    posterPath = BuildConfig.IMAGE_URL + posterPath
)
