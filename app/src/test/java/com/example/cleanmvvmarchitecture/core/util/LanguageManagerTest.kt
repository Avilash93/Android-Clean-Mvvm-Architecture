package com.example.cleanmvvmarchitecture.core.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.test.core.app.ApplicationProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import java.util.Locale

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(RobolectricTestRunner::class)
class LanguageManagerTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var context: Context
    private lateinit var languageManager: LanguageManager

    @Before
    fun setup() {
        context = ApplicationProvider.getApplicationContext()
        languageManager = LanguageManager(context)
    }

    @Test
    fun `default language should match system locale`() = runTest {
        val defaultLanguage = languageManager.currentLanguage.first()
        assertEquals(Locale.getDefault().language, defaultLanguage)
    }

    @Test
    fun `setLanguage should update current language`() = runTest {
        val newLanguage = "es"
        languageManager.setLanguage(newLanguage)
        
        val currentLanguage = languageManager.currentLanguage.first()
        assertEquals(newLanguage, currentLanguage)
    }

    @Test
    fun `applyLanguage should update system configuration`() = runTest {
        val newLanguage = "fr"
        languageManager.applyLanguage(newLanguage)
        
        val config = context.resources.configuration
        assertEquals(newLanguage, config.locales[0].language)
    }
} 