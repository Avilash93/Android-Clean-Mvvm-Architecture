package com.example.cleanmvvmarchitecture.features.auth.presentation.register

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.compose.rememberNavController
import org.junit.Rule
import org.junit.Test

class RegisterScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun registerScreen_initialState_showsEmptyFields() {
        composeTestRule.setContent {
            RegisterScreen(navController = rememberNavController())
        }

        composeTestRule
            .onNodeWithText("Name")
            .assertExists()
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("Email")
            .assertExists()
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("Password")
            .assertExists()
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("Register")
            .assertExists()
            .assertIsDisplayed()
            .assertIsNotEnabled()
    }

    @Test
    fun registerScreen_whenAllFieldsFilled_enablesRegisterButton() {
        composeTestRule.setContent {
            RegisterScreen(navController = rememberNavController())
        }

        composeTestRule
            .onNodeWithText("Name")
            .performTextInput("Test User")

        composeTestRule
            .onNodeWithText("Email")
            .performTextInput("test@example.com")

        composeTestRule
            .onNodeWithText("Password")
            .performTextInput("password123")

        composeTestRule
            .onNodeWithText("Register")
            .assertIsEnabled()
    }

    @Test
    fun registerScreen_showsLoadingIndicator_duringRegistration() {
        val fakeViewModel = FakeRegisterViewModel()
        
        composeTestRule.setContent {
            RegisterScreen(
                navController = rememberNavController(),
                viewModel = fakeViewModel
            )
        }

        // Fill all fields
        composeTestRule
            .onNodeWithText("Name")
            .performTextInput("Test User")

        composeTestRule
            .onNodeWithText("Email")
            .performTextInput("test@example.com")

        composeTestRule
            .onNodeWithText("Password")
            .performTextInput("password123")

        // Click register
        composeTestRule
            .onNodeWithText("Register")
            .performClick()

        // Verify loading indicator
        composeTestRule
            .onNodeWithTag("loading_indicator")
            .assertExists()
            .assertIsDisplayed()
    }
} 