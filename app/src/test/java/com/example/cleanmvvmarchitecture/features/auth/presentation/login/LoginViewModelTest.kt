package com.example.cleanmvvmarchitecture.features.auth.presentation.login

import com.example.cleanmvvmarchitecture.core.util.MainDispatcherRule
import com.example.cleanmvvmarchitecture.features.auth.data.repository.FakeAuthRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class LoginViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: LoginViewModel
    private lateinit var fakeRepository: FakeAuthRepository

    @Before
    fun setup() {
        fakeRepository = FakeAuthRepository()
        viewModel = LoginViewModel(fakeRepository)
    }

    @Test
    fun `login with valid credentials should emit success state`() = runTest {
        viewModel.login("test@example.com", "password123")
        
        val state = viewModel.uiState.first()
        assertTrue(state is LoginUiState.Success)
    }

    @Test
    fun `login with invalid credentials should emit error state`() = runTest {
        viewModel.login("wrong@email.com", "wrongpassword")
        
        val state = viewModel.uiState.first()
        assertTrue(state is LoginUiState.Error)
    }

    @Test
    fun `login should emit loading state initially`() = runTest {
        viewModel.login("test@example.com", "password123")
        
        val states = mutableListOf<LoginUiState>()
        viewModel.uiState.collect { states.add(it) }
        
        assertTrue(states[0] is LoginUiState.Loading)
    }

    @Test
    fun `login with empty credentials should not trigger login`() = runTest {
        viewModel.login("", "")
        
        val state = viewModel.uiState.first()
        assertTrue(state is LoginUiState.Initial)
    }

    @Test
    fun `login error message should match the exception message`() = runTest {
        fakeRepository.setShouldReturnError(true)
        viewModel.login("test@example.com", "password123")
        
        val state = viewModel.uiState.first()
        assertTrue(state is LoginUiState.Error)
        assertEquals("Invalid credentials", (state as LoginUiState.Error).message)
    }

    @Test
    fun `login state flow should emit states in correct order`() = runTest {
        val states = mutableListOf<LoginUiState>()
        val job = launch { viewModel.uiState.toList(states) }
        
        viewModel.login("test@example.com", "password123")
        
        assertTrue(states[0] is LoginUiState.Initial)
        assertTrue(states[1] is LoginUiState.Loading)
        assertTrue(states[2] is LoginUiState.Success)
        
        job.cancel()
    }
} 