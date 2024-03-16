package com.doan.example.domain.models

data class Movies(
    val page: Int,
    val results: List<Movie>,
    val totalPages: Int,
    val totalResults: Int,
) {
    data class Movie(
        val adult: Boolean,
        val backdropPath: String,
        val id: Long,
        val originalLanguage: String,
        val originalTitle: String,
        val overview: String,
        val popularity: Double,
        val posterPath: String,
        val releaseDate: String,
        val title: String,
        val video: Boolean,
        val voteAverage: Float,
        val voteCount: Long,
    )
}
