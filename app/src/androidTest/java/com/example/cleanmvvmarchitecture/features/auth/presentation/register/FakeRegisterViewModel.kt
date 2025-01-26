package com.example.cleanmvvmarchitecture.features.auth.presentation.register

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FakeRegisterViewModel : ViewModel() {
    private val _uiState = MutableStateFlow<RegisterUiState>(RegisterUiState.Initial)
    val uiState: StateFlow<RegisterUiState> = _uiState

    fun register(name: String, email: String, password: String) {
        _uiState.value = RegisterUiState.Loading
        
        if (email == "test@example.com" && password == "password123") {
            _uiState.value = RegisterUiState.Success
        } else {
            _uiState.value = RegisterUiState.Error("Registration failed")
        }
    }
} 