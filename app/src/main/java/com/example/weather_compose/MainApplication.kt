package com.example.weather_compose

import android.app.Application
import androidx.work.Configuration
import com.example.weather_compose.data.CityRepository
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltAndroidApp
class MainApplication : Application(), Configuration.Provider {
    @Inject
    lateinit var cityRepository: CityRepository

    override fun onCreate() {
        super.onCreate()
        // Load cities on app launch in a background thread
        CoroutineScope(Dispatchers.IO).launch {
            cityRepository.loadCitiesFromAssets()
            println("$cityRepository loaded")
        }
    }
    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setMinimumLoggingLevel(if (BuildConfig.DEBUG) android.util.Log.DEBUG else android.util.Log.ERROR)
            .build()
}