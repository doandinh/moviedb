package com.doan.example.data.repositories

import com.doan.example.data.extensions.flowTransform
import com.doan.example.data.remote.models.responses.toMovies
import com.doan.example.data.remote.services.ApiService
import com.doan.example.domain.models.Movies
import com.doan.example.domain.repositories.Repository
import kotlinx.coroutines.flow.Flow

class RepositoryImpl constructor(
    private val apiService: ApiService
) : Repository {

    override fun getMovies(): Flow<Movies> = flowTransform {
        apiService.getMovies().toMovies()
    }
}
