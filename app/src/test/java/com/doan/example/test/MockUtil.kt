package com.doan.example.test

import com.doan.example.domain.models.MovieDetail
import com.doan.example.domain.models.Movies

object MockUtil {

    val movies = Movies(
        page = 1,
        results = listOf(),
        totalPages = 99,
        totalResults = 100
    )

    val movieDetail = MovieDetail(
        id = 1,
        adult = false,
        backdropPath = "",
        originalLanguage = "",
        originalTitle = "",
        overview = "",
        popularity = 0.0,
        posterPath = "",
        releaseDate = "",
        title = "",
        video = false,
        voteAverage = 0.88f,
        voteCount = 10,
        budget = 200.0,
        status = ""
    )
}
