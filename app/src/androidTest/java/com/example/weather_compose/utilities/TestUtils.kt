package com.example.weather_compose.utilities

import com.example.weather_compose.models.WeatherItem

val testWeatherItems = arrayListOf(
    WeatherItem(
        latitude = 37.7749,
        longitude = -122.4494,
        resolvedAddress = "San Francisco, California, United States",
        address = "San Francisco, US",
        temperature = "15",
        tempMax = "18",
        tempMin = "12",
        icon = "partly-cloudy-day",
        conditions = "Partly Cloudy"
    ),
    WeatherItem(
            latitude = 40.7128,
            longitude = -74.0060,
            resolvedAddress = "New York, New York, United States",
            address = "New York, US",
            temperature = "20",
            tempMax = "23",
            tempMin = "17",
            icon = "clear-day",
            conditions = "Clear"
    ),
    WeatherItem(
        latitude = 51.5074,
        longitude = 0.1278,
        resolvedAddress = "London, England, United Kingdom",
        address = "London, UK",
        temperature = "12",
        tempMax = "15",
        tempMin = "9",
        icon = "rain",
        conditions = "Rainy"
    )
)
