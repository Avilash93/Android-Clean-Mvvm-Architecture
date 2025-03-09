package com.example.cleanmvvmarchitecture.core.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStoreFile
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataStoreManager @Inject constructor(private val dataStore: DataStore<Preferences>) {
    // Function to save the isLoggedIn status
    companion object{
        val PREFERENCE_DATA = "preferenceData"
        val IS_LOGGED = booleanPreferencesKey("isLoggedIn")
    }
    suspend fun setIsLoggedIn(value: Boolean) {
        dataStore.edit { it[IS_LOGGED] = value }
    }

    // Function to retrieve the isLoggedIn status as Flow
    fun isLoggedIn(): Flow<Boolean> {
        return dataStore.data.map { preferences ->
            preferences[IS_LOGGED] ?: false // Default is false
        }
    }
}