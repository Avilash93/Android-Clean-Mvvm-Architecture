package com.example.cleanmvvmarchitecture.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cleanmvvmarchitecture.features.auth.data.local.entity.UserEntity
import com.example.cleanmvvmarchitecture.features.auth.data.local.dao.UserDao

@Database(
    entities = [UserEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
} 