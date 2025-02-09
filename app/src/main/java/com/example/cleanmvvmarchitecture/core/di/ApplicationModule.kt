package com.example.cleanmvvmarchitecture.core.di

import android.content.Context
import com.example.cleanmvvmarchitecture.core.util.LanguageManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Provides
    @Singleton
    fun provideLanguageManager(
        @ApplicationContext context: Context
    ) = LanguageManager(context)
} 