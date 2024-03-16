package com.doan.example.data.remote.services

import com.doan.example.data.remote.models.responses.MovieDetailResponse
import com.doan.example.data.remote.models.responses.MoviesResponse
import retrofit2.http.*

interface ApiService {

    @GET("3/discover/movie")
    suspend fun getMovies(): MoviesResponse

    @GET("3/movie/{movieId}")
    suspend fun getMovieDetail(
        @Path("movieId") movieId: Long
    ): MovieDetailResponse
}
