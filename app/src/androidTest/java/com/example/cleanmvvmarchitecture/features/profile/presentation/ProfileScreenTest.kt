package com.example.cleanmvvmarchitecture.features.profile.presentation

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.compose.rememberNavController
import org.junit.Rule
import org.junit.Test

class ProfileScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun profileScreen_showsLoadingInitially() {
        composeTestRule.setContent {
            ProfileScreen(navController = rememberNavController())
        }

        composeTestRule
            .onNodeWithTag("loading_indicator")
            .assertExists()
            .assertIsDisplayed()
    }

    @Test
    fun profileScreen_showsUserDataWhenLoaded() {
        val fakeViewModel = FakeProfileViewModel()
        
        composeTestRule.setContent {
            ProfileScreen(
                navController = rememberNavController(),
                viewModel = fakeViewModel
            )
        }

        composeTestRule
            .onNodeWithText("John Doe")
            .assertExists()
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("john.doe@example.com")
            .assertExists()
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("Android Developer")
            .assertExists()
            .assertIsDisplayed()
    }

    @Test
    fun profileScreen_showsLogoutConfirmation() {
        composeTestRule.setContent {
            ProfileScreen(navController = rememberNavController())
        }

        // Click logout button
        composeTestRule
            .onNodeWithContentDescription("Logout")
            .performClick()

        // Verify confirmation dialog
        composeTestRule
            .onNodeWithText("Confirm Logout")
            .assertExists()
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("Are you sure you want to logout?")
            .assertExists()
            .assertIsDisplayed()
    }
} 