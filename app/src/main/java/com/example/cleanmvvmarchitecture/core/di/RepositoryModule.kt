package com.example.cleanmvvmarchitecture.core.di

import com.example.cleanmvvmarchitecture.features.auth.data.repository.AuthRepositoryImpl
import com.example.cleanmvvmarchitecture.features.auth.domain.repository.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl
    ): AuthRepository
} 