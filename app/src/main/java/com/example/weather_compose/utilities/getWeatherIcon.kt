package com.example.weather_compose.utilities

import com.example.weather_compose.R

// Utility function to map weather icon names to drawable resource IDs
fun getWeatherIcon(icon: String): Int {
    return when (icon) {
        "clear-day" -> R.drawable.ic_clear_day
        "clear-night" -> R.drawable.ic_clear_night
        "cloudy" -> R.drawable.ic_cloudy
        "fog" -> R.drawable.ic_fog
        "hail" -> R.drawable.ic_hail
        "partly-cloudy-day" -> R.drawable.ic_partly_cloudy_day
        "partly-cloudy-night" -> R.drawable.ic_partly_cloudy_night
        "rain" -> R.drawable.ic_rain
        "rain-snow" -> R.drawable.ic_rain_snow
        "rain-snow-showers-day" -> R.drawable.ic_rain_snow_showers_day
        "rain-snow-showers-night" -> R.drawable.ic_rain_snow_showers_night
        "showers-day" -> R.drawable.ic_showers_day
        "showers-night" -> R.drawable.ic_showers_night
        "sleet" -> R.drawable.ic_sleet
        "snow" -> R.drawable.ic_snow
        "snow-showers-day" -> R.drawable.ic_snow_showers_day
        "snow-showers-night" -> R.drawable.ic_snow_showers_night
        "thunder"-> R.drawable.ic_thunder
        "thunder-rain" -> R.drawable.ic_thunder_rain
        "thunder-showers-day" -> R.drawable.ic_thunder_showers_day
        "thunder-showers-night" -> R.drawable.ic_thunder_showers_night
        "wind" -> R.drawable.ic_wind
        else -> R.drawable.ic_clear_day
    }
}