package com.example.cleanmvvmarchitecture.core.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.cleanmvvmarchitecture.core.navigation.NavRoutes

sealed class BottomNavItem(
    val route: String,
    val titleResId: Int,
    val icon: androidx.compose.ui.graphics.vector.ImageVector
) {
    object Home : BottomNavItem(
        route = NavRoutes.Home.route,
        titleResId = com.example.cleanmvvmarchitecture.R.string.home,
        icon = Icons.Default.Home
    )
    object Search : BottomNavItem(
        route = NavRoutes.Search.route,
        titleResId = com.example.cleanmvvmarchitecture.R.string.search,
        icon = Icons.Default.Search
    )
    object Profile : BottomNavItem(
        route = NavRoutes.Profile.route,
        titleResId = com.example.cleanmvvmarchitecture.R.string.profile,
        icon = Icons.Default.Person
    )
}

@Composable
fun AppBottomNavigation(
    navController: NavController
) {
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Search,
        BottomNavItem.Profile
    )

    NavigationBar {
        val navBackStackEntry = navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry.value?.destination?.route

        items.forEach { item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = null) },
                label = { Text(stringResource(item.titleResId)) },
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(NavRoutes.Home.route) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
} 