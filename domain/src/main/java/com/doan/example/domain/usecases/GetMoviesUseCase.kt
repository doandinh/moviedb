package com.doan.example.domain.usecases

import com.doan.example.domain.models.Movies
import com.doan.example.domain.repositories.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(private val repository: Repository) {

    operator fun invoke(): Flow<Movies> {
        return repository.getMovies()
    }
}
