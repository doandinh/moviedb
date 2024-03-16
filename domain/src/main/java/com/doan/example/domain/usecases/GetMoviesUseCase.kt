package com.doan.example.domain.usecases

import com.doan.example.domain.models.Movies
import com.doan.example.domain.repositories.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(private val movieRepository: MovieRepository) {

    operator fun invoke(): Flow<Movies> {
        return movieRepository.getMovies()
    }
}
