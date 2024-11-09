package com.example.weather_compose.test

import com.example.weather_compose.R
import com.example.weather_compose.utilities.getWeatherIcon
import org.junit.Assert.assertEquals
import org.junit.Test

class WeatherIconUtilTest {
    @Test
    fun testGetWeatherIconClearDay() {
        val result = getWeatherIcon("clear-day")
        assertEquals(R.drawable.ic_clear_day, result)
    }

    @Test
    fun testGetWeatherIconClearNight() {
        val result = getWeatherIcon("clear-night")
        assertEquals(R.drawable.ic_clear_night, result)
    }

    @Test
    fun testGetWeatherIconCloudy() {
        val result = getWeatherIcon("cloudy")
        assertEquals(R.drawable.ic_cloudy, result)
    }

    @Test
    fun testGetWeatherIconFog() {
        val result = getWeatherIcon("fog")
        assertEquals(R.drawable.ic_fog, result)
    }

    @Test
    fun testGetWeatherIconRain() {
        val result = getWeatherIcon("rain")
        assertEquals(R.drawable.ic_rain, result)
    }
    
    @Test
    fun testGetWeatherIconUnknown() {
        val result = getWeatherIcon("unknown")
        assertEquals(R.drawable.ic_clear_day, result) // Default icon for unknown
    }

    @Test
    fun testGetWeatherIconPartlyCloudyDay() {
        val result = getWeatherIcon("partly-cloudy-day")
        assertEquals(R.drawable.ic_partly_cloudy_day, result)
    }

    @Test
    fun testGetWeatherIconThunder() {
        val result = getWeatherIcon("thunder")
        assertEquals(R.drawable.ic_thunder, result)
    }

    @Test
    fun testGetWeatherIconWind() {
        val result = getWeatherIcon("wind")
        assertEquals(R.drawable.ic_wind, result)
    }




}