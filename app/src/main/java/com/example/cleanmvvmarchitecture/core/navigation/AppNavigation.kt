package com.example.cleanmvvmarchitecture.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cleanmvvmarchitecture.features.auth.presentation.login.LoginScreen
import com.example.cleanmvvmarchitecture.features.auth.presentation.register.RegisterScreen
import com.example.cleanmvvmarchitecture.features.home.presentation.HomeScreen
import com.example.cleanmvvmarchitecture.features.profile.presentation.ProfileScreen
import com.example.cleanmvvmarchitecture.features.splash.presentation.SplashScreen

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = NavRoutes.Splash.route
    ) {
        composable(NavRoutes.Splash.route) {
            SplashScreen(navController)
        }
        composable(NavRoutes.Login.route) {
            LoginScreen(navController)
        }
        composable(NavRoutes.Register.route) {
            RegisterScreen(navController)
        }
        composable(NavRoutes.Home.route) {
            HomeScreen(navController)
        }
        composable(NavRoutes.Profile.route) {
            ProfileScreen(navController)
        }
    }
} 