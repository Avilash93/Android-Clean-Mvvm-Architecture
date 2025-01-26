package com.example.cleanmvvmarchitecture.features.auth.data.repository

import com.example.cleanmvvmarchitecture.features.auth.data.model.AuthResponse
import com.example.cleanmvvmarchitecture.features.auth.data.model.LoginRequest
import com.example.cleanmvvmarchitecture.features.auth.data.model.RegisterRequest
import com.example.cleanmvvmarchitecture.features.auth.data.model.UserDto
import com.example.cleanmvvmarchitecture.features.auth.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeAuthRepository : AuthRepository {
    private var isUserLoggedIn = false
    private var shouldReturnError = false
    private val validEmail = "test@example.com"
    private val validPassword = "password123"

    fun setShouldReturnError(value: Boolean) {
        shouldReturnError = value
    }

    override suspend fun login(request: LoginRequest): Flow<Result<AuthResponse>> = flow {
        if (shouldReturnError) {
            emit(Result.failure(Exception("Invalid credentials")))
            return@flow
        }

        if (request.email == validEmail && request.password == validPassword) {
            isUserLoggedIn = true
            emit(Result.success(
                AuthResponse(
                    token = "fake_token",
                    user = UserDto(
                        id = "1",
                        email = request.email,
                        name = "Test User"
                    )
                )
            ))
        } else {
            emit(Result.failure(Exception("Invalid credentials")))
        }
    }

    override suspend fun register(request: RegisterRequest): Flow<Result<AuthResponse>> = flow {
        if (shouldReturnError) {
            emit(Result.failure(Exception("Registration failed")))
            return@flow
        }

        isUserLoggedIn = true
        emit(Result.success(
            AuthResponse(
                token = "fake_token",
                user = UserDto(
                    id = "1",
                    email = request.email,
                    name = request.name
                )
            )
        ))
    }

    override suspend fun logout() {
        isUserLoggedIn = false
    }

    override fun isLoggedIn(): Flow<Boolean> = flow {
        emit(isUserLoggedIn)
    }
} 