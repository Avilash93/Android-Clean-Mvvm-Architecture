package com.example.cleanmvvmarchitecture.features.auth.domain.repository

import com.example.cleanmvvmarchitecture.features.auth.data.model.AuthResponse
import com.example.cleanmvvmarchitecture.features.auth.data.model.LoginRequest
import com.example.cleanmvvmarchitecture.features.auth.data.model.RegisterRequest
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun login(request: LoginRequest): Flow<Result<AuthResponse>>
    suspend fun register(request: RegisterRequest): Flow<Result<AuthResponse>>
    suspend fun logout()
    fun isLoggedIn(): Flow<Boolean>
} 