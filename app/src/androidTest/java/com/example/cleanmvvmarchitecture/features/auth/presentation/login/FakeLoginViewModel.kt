package com.example.cleanmvvmarchitecture.features.auth.presentation.login

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FakeLoginViewModel : ViewModel() {
    private val _uiState = MutableStateFlow<LoginUiState>(LoginUiState.Initial)
    val uiState: StateFlow<LoginUiState> = _uiState

    fun login(email: String, password: String) {
        _uiState.value = LoginUiState.Loading
        
        if (email == "test@example.com" && password == "password123") {
            _uiState.value = LoginUiState.Success
        } else {
            _uiState.value = LoginUiState.Error("Invalid credentials")
        }
    }
} 