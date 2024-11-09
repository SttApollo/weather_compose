package com.example.weather_compose.utilities

import com.example.weather_compose.data.DailyForecastResponse
import java.time.LocalTime
import java.time.format.DateTimeFormatter


fun formatTime(datetime: String, dailyForecast: DailyForecastResponse): String {
    // Check if the time matches sunrise or sunset
    if (datetime == dailyForecast.sunrise) return "sunrise"
    if (datetime == dailyForecast.sunset) return "sunset"

    // Parse the datetime and convert to 12-hour format

    // Define the input time format (24-hour format)
    val timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss", java.util.Locale.getDefault())

    // Parse the input datetime string into a LocalTime object
    val time = LocalTime.parse(datetime, timeFormat)

    // Define the output time format (12-hour format with AM/PM)
    val outputFormat = DateTimeFormatter.ofPattern("h a", java.util.Locale.getDefault())

    // Format the parsed time into the desired output format
    return time.format(outputFormat)
}