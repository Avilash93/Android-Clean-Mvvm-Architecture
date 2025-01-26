package com.example.cleanmvvmarchitecture.core.navigation

sealed class NavRoutes(val route: String) {
    object Splash : NavRoutes("splash")
    object Login : NavRoutes("login")
    object Register : NavRoutes("register")
    object Home : NavRoutes("home")
    object Profile : NavRoutes("profile")
    object Search : NavRoutes("search")
}