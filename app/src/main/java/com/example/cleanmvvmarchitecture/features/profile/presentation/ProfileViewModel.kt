package com.example.cleanmvvmarchitecture.features.profile.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanmvvmarchitecture.features.auth.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<ProfileUiState>(ProfileUiState.Loading)
    val uiState: StateFlow<ProfileUiState> = _uiState

    init {
        loadProfile()
    }

    private fun loadProfile() {
        // Simulate loading profile data
        _uiState.value = ProfileUiState.Success(
            name = "John Doe",
            email = "john.doe@example.com",
            bio = "Android Developer"
        )
    }

    fun logout() {
        viewModelScope.launch {
            authRepository.logout()
            // Navigate to login screen will be handled by the repository's isLoggedIn flow
        }
    }
}

sealed class ProfileUiState {
    object Loading : ProfileUiState()
    data class Success(
        val name: String,
        val email: String,
        val bio: String
    ) : ProfileUiState()
    data class Error(val message: String) : ProfileUiState()
} 