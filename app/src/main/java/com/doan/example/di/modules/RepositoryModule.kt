package com.doan.example.di.modules

import com.doan.example.data.remote.services.ApiService
import com.doan.example.data.repositories.RepositoryImpl
import com.doan.example.domain.repositories.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class RepositoryModule {

    @Provides
    fun provideRepository(apiService: ApiService): Repository = RepositoryImpl(apiService)
}
