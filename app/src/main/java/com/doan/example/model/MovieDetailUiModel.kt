package com.doan.example.model

import android.os.Parcelable
import com.doan.example.BuildConfig
import com.doan.example.domain.models.MovieDetail
import kotlinx.parcelize.Parcelize

private const val IMAGE_SIZE = 500

@Parcelize
data class MovieDetailUiModel(
    val id: Long,
    val posterPath: String,
    val title: String,
    val originalTitle: String,
    val overview: String,
    val popularity: String,
    val status: String,
) : Parcelable

fun MovieDetail.toUiModel() = MovieDetailUiModel(
    id = id,
    title = title,
    posterPath = "${BuildConfig.IMAGE_URL}$IMAGE_SIZE$posterPath",
    originalTitle = originalTitle,
    overview = overview,
    popularity = String.format("%.2f", popularity),
    status = status
)
