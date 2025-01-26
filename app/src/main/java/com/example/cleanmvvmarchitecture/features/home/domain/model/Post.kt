package com.example.cleanmvvmarchitecture.features.home.domain.model

data class Post(
    val id: String,
    val title: String,
    val description: String,
    val imageUrl: String?,
    val author: String,
    val createdAt: String
) 