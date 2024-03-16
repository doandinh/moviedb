package com.doan.example.domain.repositories

import com.doan.example.domain.models.MovieDetail
import com.doan.example.domain.models.Movies
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getMovies(): Flow<Movies>

    fun getMovieDetail(movieId: Long): Flow<MovieDetail>
}
