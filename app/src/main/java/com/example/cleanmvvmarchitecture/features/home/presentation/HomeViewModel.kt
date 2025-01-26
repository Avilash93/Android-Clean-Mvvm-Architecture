package com.example.cleanmvvmarchitecture.features.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanmvvmarchitecture.features.auth.domain.repository.AuthRepository
import com.example.cleanmvvmarchitecture.features.home.domain.model.Post
import com.example.cleanmvvmarchitecture.core.domain.model.Language
import com.example.cleanmvvmarchitecture.core.util.LanguageManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val languageManager: LanguageManager
) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState

    val currentLanguage = languageManager.currentLanguage

    init {
        loadPosts()
    }

    private fun loadPosts() {
        viewModelScope.launch {
            // Simulate loading posts
            _uiState.value = HomeUiState.Success(
                posts = listOf(
                    Post(
                        id = "1",
                        title = "Sample Post 1",
                        description = "This is a sample post description",
                        imageUrl = null,
                        author = "John Doe",
                        createdAt = "2 hours ago"
                    ),
                    Post(
                        id = "2",
                        title = "Sample Post 2",
                        description = "Another sample post description",
                        imageUrl = null,
                        author = "Jane Smith",
                        createdAt = "5 hours ago"
                    )
                )
            )
        }
    }

    fun logout() {
        viewModelScope.launch {
            authRepository.logout()
        }
    }

    fun setLanguage(languageCode: String) {
        viewModelScope.launch {
            languageManager.setLanguage(languageCode)
            languageManager.applyLanguage(languageCode)
        }
    }
}

sealed class HomeUiState {
    object Loading : HomeUiState()
    data class Success(val posts: List<Post>) : HomeUiState()
    data class Error(val message: String) : HomeUiState()
} 