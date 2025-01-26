package com.example.cleanmvvmarchitecture.features.home.presentation

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.compose.rememberNavController
import org.junit.Rule
import org.junit.Test

class HomeScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun homeScreen_showsLoadingInitially() {
        composeTestRule.setContent {
            HomeScreen(navController = rememberNavController())
        }

        composeTestRule
            .onNodeWithTag("loading_indicator")
            .assertExists()
            .assertIsDisplayed()
    }

    @Test
    fun homeScreen_showsPostsWhenLoaded() {
        val fakeViewModel = FakeHomeViewModel()
        
        composeTestRule.setContent {
            HomeScreen(
                navController = rememberNavController(),
                viewModel = fakeViewModel
            )
        }

        // Verify posts are displayed
        composeTestRule
            .onNodeWithText("Sample Post 1")
            .assertExists()
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("Sample Post 2")
            .assertExists()
            .assertIsDisplayed()
    }

    @Test
    fun homeScreen_showsDrawerWhenMenuClicked() {
        composeTestRule.setContent {
            HomeScreen(navController = rememberNavController())
        }

        // Click menu button
        composeTestRule
            .onNodeWithContentDescription("Menu")
            .performClick()

        // Verify drawer items are displayed
        composeTestRule
            .onNodeWithText("Language")
            .assertExists()
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("Settings")
            .assertExists()
            .assertIsDisplayed()
    }

    @Test
    fun homeScreen_showsLanguageDialogWhenLanguageClicked() {
        composeTestRule.setContent {
            HomeScreen(navController = rememberNavController())
        }

        // Open drawer
        composeTestRule
            .onNodeWithContentDescription("Menu")
            .performClick()

        // Click language option
        composeTestRule
            .onNodeWithText("Language")
            .performClick()

        // Verify language dialog is shown
        composeTestRule
            .onNodeWithText("Select Language")
            .assertExists()
            .assertIsDisplayed()

        // Verify language options are shown
        composeTestRule
            .onNodeWithText("English")
            .assertExists()
            .assertIsDisplayed()
    }
} 