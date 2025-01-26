package com.example.cleanmvvmarchitecture.core.config

import com.example.cleanmvvmarchitecture.BuildConfig
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppConfig @Inject constructor() {
    val baseUrl: String = BuildConfig.BASE_URL
    val apiKey: String = BuildConfig.API_KEY
    val isLoggingEnabled: Boolean = BuildConfig.ENABLE_LOGGING
    val isDebug: Boolean = BuildConfig.DEBUG
    val versionName: String = BuildConfig.VERSION_NAME
    val versionCode: Int = BuildConfig.VERSION_CODE
    
    val environment: Environment = when {
        BuildConfig.DEBUG -> Environment.DEVELOPMENT
        BuildConfig.APPLICATION_ID.contains(".staging") -> Environment.STAGING
        else -> Environment.PRODUCTION
    }
}

enum class Environment {
    DEVELOPMENT,
    STAGING,
    PRODUCTION
} 