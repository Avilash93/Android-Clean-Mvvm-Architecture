package com.example.cleanmvvmarchitecture

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class ApplicationManger : Application() {
    override fun onCreate() {
        super.onCreate()
        plantTimber()
    }

    /**
     * Plan Timber for Debug.
     */
    private fun plantTimber() {
        if(BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())
    }
}