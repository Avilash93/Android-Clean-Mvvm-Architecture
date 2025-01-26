package com.example.cleanmvvmarchitecture.features.navigation

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cleanmvvmarchitecture.core.navigation.NavRoutes
import org.junit.Rule
import org.junit.Test

class NavigationTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun navigation_fromLoginToRegister() {
        composeTestRule.setContent {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = NavRoutes.Login.route
            ) {
                composable(NavRoutes.Login.route) {
                    LoginScreen(navController = navController)
                }
                composable(NavRoutes.Register.route) {
                    RegisterScreen(navController = navController)
                }
            }
        }

        // Click register link
        composeTestRule
            .onNodeWithText("Don't have an account? Sign up")
            .performClick()

        // Verify on register screen
        composeTestRule
            .onNodeWithText("Create Account")
            .assertExists()
            .assertIsDisplayed()
    }

    @Test
    fun navigation_fromRegisterToLogin() {
        composeTestRule.setContent {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = NavRoutes.Register.route
            ) {
                composable(NavRoutes.Login.route) {
                    LoginScreen(navController = navController)
                }
                composable(NavRoutes.Register.route) {
                    RegisterScreen(navController = navController)
                }
            }
        }

        // Click login link
        composeTestRule
            .onNodeWithText("Already have an account? Sign in")
            .performClick()

        // Verify on login screen
        composeTestRule
            .onNodeWithText("Welcome Back")
            .assertExists()
            .assertIsDisplayed()
    }
} 