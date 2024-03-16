package com.doan.example.domain.usecases

import com.doan.example.domain.models.MovieDetail
import com.doan.example.domain.repositories.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieDetailUseCase @Inject constructor(private val movieRepository: MovieRepository) {

    operator fun invoke(movieId: Long): Flow<MovieDetail> {
        return movieRepository.getMovieDetail(movieId)
    }
}
