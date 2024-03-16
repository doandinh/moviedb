package com.doan.example.domain.repositories

import com.doan.example.domain.models.Movies
import kotlinx.coroutines.flow.Flow

interface Repository {

    fun getMovies(): Flow<Movies>
}
