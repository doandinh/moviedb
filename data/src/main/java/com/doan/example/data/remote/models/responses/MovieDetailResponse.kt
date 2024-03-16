package com.doan.example.data.remote.models.responses

import com.doan.example.domain.models.MovieDetail
import com.squareup.moshi.Json

data class MovieDetailResponse(
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
    @Json(name = "budget") val budget: Double? = null,
    @Json(name = "status") val status: String? = null,
) {
}

fun MovieDetailResponse.toMovieDetail() = MovieDetail(
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
    voteCount = voteCount ?: 0,
    budget = budget ?: 0.0,
    status = status.orEmpty()
)


