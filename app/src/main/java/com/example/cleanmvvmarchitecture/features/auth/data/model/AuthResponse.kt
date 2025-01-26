package com.example.cleanmvvmarchitecture.features.auth.data.model

data class AuthResponse(
    val token: String,
    val user: UserDto
)

data class UserDto(
    val id: String,
    val email: String,
    val name: String
) 