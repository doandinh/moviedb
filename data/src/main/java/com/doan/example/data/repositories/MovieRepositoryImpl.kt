package com.doan.example.data.repositories

import com.doan.example.data.extensions.flowTransform
import com.doan.example.data.remote.models.responses.toMovieDetail
import com.doan.example.data.remote.models.responses.toMovies
import com.doan.example.data.remote.services.ApiService
import com.doan.example.domain.models.MovieDetail
import com.doan.example.domain.models.Movies
import com.doan.example.domain.repositories.MovieRepository
import kotlinx.coroutines.flow.Flow

class MovieRepositoryImpl constructor(
    private val apiService: ApiService
) : MovieRepository {

    override fun getMovies(): Flow<Movies> = flowTransform {
        apiService.getMovies().toMovies()
    }

    override fun getMovieDetail(movieId: Long): Flow<MovieDetail> = flowTransform {
        apiService.getMovieDetail(movieId).toMovieDetail()
    }
}
