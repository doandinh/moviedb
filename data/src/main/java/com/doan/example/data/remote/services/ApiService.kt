package com.doan.example.data.remote.services

import com.doan.example.data.remote.models.responses.MoviesResponse
import retrofit2.http.GET

interface ApiService {

    @GET("3/discover/movie")
    suspend fun getMovies(): MoviesResponse
}
