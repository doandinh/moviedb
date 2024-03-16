package com.doan.example.di.modules

import com.doan.example.data.remote.services.ApiService
import com.doan.example.data.repositories.MovieRepositoryImpl
import com.doan.example.domain.repositories.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    fun provideRepository(apiService: ApiService): MovieRepository = MovieRepositoryImpl(apiService)
}
