package com.taka.taka

import android.app.Application
import com.taka.taka.data.datasource.local.SharedPreference
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        // pref 초기화
        SharedPreference.getInstance(this)
    }
}