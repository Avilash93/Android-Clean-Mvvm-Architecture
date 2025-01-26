package com.example.cleanmvvmarchitecture.features.profile.presentation

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
class ProfileViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: ProfileViewModel
    private lateinit var fakeAuthRepository: FakeAuthRepository

    @Before
    fun setup() {
        fakeAuthRepository = FakeAuthRepository()
        viewModel = ProfileViewModel(fakeAuthRepository)
    }

    @Test
    fun `initial state should be loading`() = runTest {
        val initialState = viewModel.uiState.first()
        assertTrue(initialState is ProfileUiState.Loading)
    }

    @Test
    fun `loadProfile should emit success state with profile data`() = runTest {
        val state = viewModel.uiState.first()
        assertTrue(state is ProfileUiState.Success)
        assertEquals("John Doe", (state as ProfileUiState.Success).name)
    }

    @Test
    fun `logout should call repository logout`() = runTest {
        viewModel.logout()
        assertTrue(!fakeAuthRepository.isLoggedIn().first())
    }
} 