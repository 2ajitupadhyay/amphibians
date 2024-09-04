package com.ajidroid.amphibians

import android.app.Application
import com.ajidroid.amphibians.data.AppContainer
import com.ajidroid.amphibians.data.DefaultAppContainer

class AmphibiansApplication : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}