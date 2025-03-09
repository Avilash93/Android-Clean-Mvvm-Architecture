package com.example.cleanmvvmarchitecture.features.auth.data.model

data class AuthResponse(
    val id: String,
    val username: String,
    val email: String,
    val firstName: String,
    val lastName: String,
    val gender: String,
    val image: String,
    val accessToken: String,
    val refreshToken: String,
)