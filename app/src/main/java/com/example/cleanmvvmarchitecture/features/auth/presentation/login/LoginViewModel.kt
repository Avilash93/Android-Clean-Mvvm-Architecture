package com.example.cleanmvvmarchitecture.features.auth.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanmvvmarchitecture.core.datastore.DataStoreManager
import com.example.cleanmvvmarchitecture.features.auth.data.model.LoginRequest
import com.example.cleanmvvmarchitecture.features.auth.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val dataStoreManager: DataStoreManager
) : ViewModel() {

    private val _uiState = MutableStateFlow<LoginUiState>(LoginUiState.Initial)
    val uiState: StateFlow<LoginUiState> = _uiState

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _uiState.value = LoginUiState.Loading
            authRepository.login(LoginRequest(email, password))
                .collect { result ->
                    _uiState.value = when {
                        result.isSuccess -> {
                            dataStoreManager.setIsLoggedIn(true)
                            LoginUiState.Success}
                        result.isFailure -> LoginUiState.Error(
                            result.exceptionOrNull()?.message ?: "Unknown error"
                        )
                        else -> LoginUiState.Error("Unknown error")
                    }
                }
        }
    }
}

sealed class LoginUiState {
    object Initial : LoginUiState()
    object Loading : LoginUiState()
    object Success : LoginUiState()
    data class Error(val message: String) : LoginUiState()
} 