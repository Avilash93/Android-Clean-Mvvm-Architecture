package com.example.cleanmvvmarchitecture.features.auth.presentation.register

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
class RegisterViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: RegisterViewModel
    private lateinit var fakeRepository: FakeAuthRepository

    @Before
    fun setup() {
        fakeRepository = FakeAuthRepository()
        viewModel = RegisterViewModel(fakeRepository)
    }

    @Test
    fun `register with valid data should emit success state`() = runTest {
        viewModel.register(
            name = "Test User",
            email = "test@example.com",
            password = "password123"
        )
        
        val state = viewModel.uiState.first()
        assertTrue(state is RegisterUiState.Success)
    }

    @Test
    fun `register with empty fields should not trigger registration`() = runTest {
        viewModel.register("", "", "")
        
        val state = viewModel.uiState.first()
        assertTrue(state is RegisterUiState.Initial)
    }

    @Test
    fun `register error should contain error message`() = runTest {
        fakeRepository.setShouldReturnError(true)
        viewModel.register("Test User", "test@example.com", "password123")
        
        val state = viewModel.uiState.first()
        assertTrue(state is RegisterUiState.Error)
        assertEquals("Registration failed", (state as RegisterUiState.Error).message)
    }

    @Test
    fun `register should emit states in correct order`() = runTest {
        val states = mutableListOf<RegisterUiState>()
        val job = launch { viewModel.uiState.toList(states) }
        
        viewModel.register("Test User", "test@example.com", "password123")
        
        assertTrue(states[0] is RegisterUiState.Initial)
        assertTrue(states[1] is RegisterUiState.Loading)
        assertTrue(states[2] is RegisterUiState.Success)
        
        job.cancel()
    }
} 