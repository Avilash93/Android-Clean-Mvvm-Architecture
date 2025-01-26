package com.example.cleanmvvmarchitecture.features.home.presentation

import com.example.cleanmvvmarchitecture.core.util.MainDispatcherRule
import com.example.cleanmvvmarchitecture.features.auth.data.repository.FakeAuthRepository
import com.example.cleanmvvmarchitecture.core.util.LanguageManager
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: HomeViewModel
    private lateinit var fakeAuthRepository: FakeAuthRepository
    private lateinit var languageManager: LanguageManager

    @Before
    fun setup() {
        fakeAuthRepository = FakeAuthRepository()
        languageManager = mock()
        viewModel = HomeViewModel(fakeAuthRepository, languageManager)
    }

    @Test
    fun `initial state should be loading`() = runTest {
        val initialState = viewModel.uiState.first()
        assertTrue(initialState is HomeUiState.Loading)
    }

    @Test
    fun `loadPosts should emit success state with posts`() = runTest {
        val state = viewModel.uiState.first()
        assertTrue(state is HomeUiState.Success)
        assertTrue((state as HomeUiState.Success).posts.isNotEmpty())
    }

    @Test
    fun `logout should call repository logout`() = runTest {
        viewModel.logout()
        assertTrue(!fakeAuthRepository.isLoggedIn().first())
    }

    @Test
    fun `setLanguage should update language manager`() = runTest {
        val newLanguage = "es"
        viewModel.setLanguage(newLanguage)
        verify(languageManager).setLanguage(newLanguage)
        verify(languageManager).applyLanguage(newLanguage)
    }
} 