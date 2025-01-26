package com.example.cleanmvvmarchitecture.features.auth.presentation.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanmvvmarchitecture.features.auth.data.model.RegisterRequest
import com.example.cleanmvvmarchitecture.features.auth.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<RegisterUiState>(RegisterUiState.Initial)
    val uiState: StateFlow<RegisterUiState> = _uiState

    fun register(name: String, email: String, password: String) {
        viewModelScope.launch {
            _uiState.value = RegisterUiState.Loading
            authRepository.register(RegisterRequest(email, password, name))
                .collect { result ->
                    _uiState.value = when {
                        result.isSuccess -> RegisterUiState.Success
                        result.isFailure -> RegisterUiState.Error(
                            result.exceptionOrNull()?.message ?: "Unknown error"
                        )
                        else -> RegisterUiState.Error("Unknown error")
                    }
                }
        }
    }
}

sealed class RegisterUiState {
    object Initial : RegisterUiState()
    object Loading : RegisterUiState()
    object Success : RegisterUiState()
    data class Error(val message: String) : RegisterUiState()
} 