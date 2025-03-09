package com.example.cleanmvvmarchitecture.features.auth.data.repository

import com.example.cleanmvvmarchitecture.core.datastore.DataStoreManager
import com.example.cleanmvvmarchitecture.features.auth.data.model.AuthResponse
import com.example.cleanmvvmarchitecture.features.auth.data.model.LoginRequest
import com.example.cleanmvvmarchitecture.features.auth.data.model.RegisterRequest
import com.example.cleanmvvmarchitecture.features.auth.data.remote.AuthApi
import com.example.cleanmvvmarchitecture.features.auth.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val api: AuthApi,
    private val dataStoreManager: DataStoreManager
) : AuthRepository {
    override suspend fun login(request: LoginRequest): Flow<Result<AuthResponse>> = flow {
        try {
            val response = api.login(request)
            emit(Result.success(response))
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }

    override suspend fun register(request: RegisterRequest): Flow<Result<AuthResponse>> = flow {
        try {
            val response = api.register(request)
            emit(Result.success(response))
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }

    override suspend fun logout() {
        // Implement logout logic
    }

    override fun isLoggedIn(): Flow<Boolean> = flow {
        // Implement check login status logic
        emit(dataStoreManager.isLoggedIn().first())
    }
} 