package com.example.cleanmvvmarchitecture.core.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.rememberUpdatedState
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import timber.log.Timber
import androidx.compose.runtime.getValue

/**
 * This class contains all the utility methods only with no dependencies.
 */
object Utils {
    /**
     * Print Debug log
     * @param tag
     * @param throwable
     */
    fun printDebugLog(tag: String, throwable: Throwable) {
        Timber.tag(tag).d(throwable)
    }

    /**
     * Print Error log
     * @param tag
     * @param throwable
     */
    fun printErrorLog(tag: String, throwable: Throwable) {
        Timber.tag(tag).e(throwable)
    }

    /**
     * Print Info log
     * @param tag
     * @param throwable
     */
    fun printInfoLog(tag: String, throwable: Throwable) {
        Timber.tag(tag).i(throwable)
    }

    @Composable
    fun LogCurrentScreenLifecycle(navController: NavController) {
        val currentBackStackEntry by navController.currentBackStackEntryAsState()
        val lifecycle = currentBackStackEntry?.lifecycle

        DisposableEffect(lifecycle) {
            val screenName = currentBackStackEntry?.destination?.route ?: "UnknownScreen"

            if (lifecycle != null && screenName != "UnknownScreen") {
                val observer = LifecycleEventObserver { _, event ->
                    Timber.tag("LifecycleLog").d("$screenName - $event")
                }

                lifecycle.addObserver(observer)

                onDispose {
                    lifecycle.removeObserver(observer)
                }
            }

            onDispose { } // Ensures proper cleanup
        }
    }


}