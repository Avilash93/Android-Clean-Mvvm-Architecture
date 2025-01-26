package com.example.cleanmvvmarchitecture.features.auth.data.repository

import com.example.cleanmvvmarchitecture.core.util.MainDispatcherRule
import com.example.cleanmvvmarchitecture.features.auth.data.model.AuthResponse
import com.example.cleanmvvmarchitecture.features.auth.data.model.LoginRequest
import com.example.cleanmvvmarchitecture.features.auth.data.model.RegisterRequest
import com.example.cleanmvvmarchitecture.features.auth.data.remote.AuthApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class AuthRepositoryImplTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var repository: AuthRepositoryImpl
    private lateinit var api: AuthApi

    @Before
    fun setup() {
        api = mock()
        repository = AuthRepositoryImpl(api)
    }

    @Test
    fun `login success should emit success result`() = runTest {
        val request = LoginRequest("test@example.com", "password123")
        val response = AuthResponse(
            token = "token",
            user = mock()
        )

        whenever(api.login(request)).thenReturn(response)

        val result = repository.login(request).first()
        assertTrue(result.isSuccess)
    }

    @Test
    fun `login failure should emit error result`() = runTest {
        val request = LoginRequest("test@example.com", "password123")
        whenever(api.login(request)).thenThrow(RuntimeException("Network error"))

        val result = repository.login(request).first()
        assertTrue(result.isFailure)
    }

    @Test
    fun `register success should emit success result`() = runTest {
        val request = RegisterRequest(
            email = "test@example.com",
            password = "password123",
            name = "Test User"
        )
        val response = AuthResponse(
            token = "token",
            user = mock()
        )

        whenever(api.register(request)).thenReturn(response)

        val result = repository.register(request).first()
        assertTrue(result.isSuccess)
    }

    @Test
    fun `register failure should emit error result`() = runTest {
        val request = RegisterRequest(
            email = "test@example.com",
            password = "password123",
            name = "Test User"
        )
        whenever(api.register(request)).thenThrow(RuntimeException("Network error"))

        val result = repository.register(request).first()
        assertTrue(result.isFailure)
    }

    @Test
    fun `isLoggedIn should emit correct state`() = runTest {
        // Initial state
        assertFalse(repository.isLoggedIn().first())

        // After successful login
        val loginRequest = LoginRequest("test@example.com", "password123")
        whenever(api.login(loginRequest)).thenReturn(mock())
        repository.login(loginRequest).first()

        assertTrue(repository.isLoggedIn().first())

        // After logout
        repository.logout()
        assertFalse(repository.isLoggedIn().first())
    }
} 