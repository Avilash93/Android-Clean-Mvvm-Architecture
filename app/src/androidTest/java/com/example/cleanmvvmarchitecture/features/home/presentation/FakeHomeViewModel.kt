package com.example.cleanmvvmarchitecture.features.home.presentation

import androidx.lifecycle.ViewModel
import com.example.cleanmvvmarchitecture.features.home.domain.model.Post
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FakeHomeViewModel : ViewModel() {
    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState

    init {
        loadPosts()
    }

    private fun loadPosts() {
        _uiState.value = HomeUiState.Success(
            posts = listOf(
                Post(
                    id = "1",
                    title = "Sample Post 1",
                    description = "Description 1",
                    imageUrl = null,
                    author = "John Doe",
                    createdAt = "2 hours ago"
                ),
                Post(
                    id = "2",
                    title = "Sample Post 2",
                    description = "Description 2",
                    imageUrl = null,
                    author = "Jane Smith",
                    createdAt = "5 hours ago"
                )
            )
        )
    }
} 