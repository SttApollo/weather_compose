package com.example.weather_compose.location

import android.location.Location

interface LocationTracker {
    suspend fun getCurrentLocation(): Location?
}