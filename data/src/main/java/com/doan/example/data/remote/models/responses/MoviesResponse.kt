package com.doan.example.data.remote.models.responses

import com.doan.example.domain.models.Movies
import com.squareup.moshi.Json

data class MoviesResponse(
    @Json(name = "page") val page: Int? = null,
    @Json(name = "results") val results: List<MovieResponse>? = null,
    @Json(name = "total_pages") val totalPages: Int? = null,
    @Json(name = "total_results") val totalResults: Int? = null,
) {
    data class MovieResponse(
        @Json(name = "adult") val adult: Boolean? = null,
        @Json(name = "backdrop_path") val backdropPath: String? = null,
        @Json(name = "id") val id: Long? = null,
        @Json(name = "original_language") val originalLanguage: String? = null,
        @Json(name = "original_title") val originalTitle: String? = null,
        @Json(name = "overview") val overview: String? = null,
        @Json(name = "popularity") val popularity: Double? = null,
        @Json(name = "poster_path") val posterPath: String? = null,
        @Json(name = "release_date") val releaseDate: String? = null,
        @Json(name = "title") val title: String? = null,
        @Json(name = "video") val video: Boolean? = null,
        @Json(name = "vote_average") val voteAverage: Float? = null,
        @Json(name = "vote_count") val voteCount: Long? = null,
    )
}

fun MoviesResponse.toMovies() = Movies(
    page = page ?: 0,
    results = results.toMovieList(),
    totalPages = totalPages ?: 0,
    totalResults = totalResults ?: 0
)

private fun List<MoviesResponse.MovieResponse>?.toMovieList() = this?.map { it.toMovie() }.orEmpty()

private fun MoviesResponse.MovieResponse.toMovie() = Movies.Movie(
    id = id ?: 0,
    adult = adult ?: false,
    backdropPath = backdropPath.orEmpty(),
    originalLanguage = originalLanguage.orEmpty(),
    originalTitle = originalTitle.orEmpty(),
    overview = overview.orEmpty(),
    popularity = popularity ?: 0.0,
    posterPath = posterPath.orEmpty(),
    releaseDate = releaseDate.orEmpty(),
    title = title.orEmpty(),
    video = video ?: false,
    voteAverage = voteAverage ?: 0f,
    voteCount = voteCount ?: 0

)


