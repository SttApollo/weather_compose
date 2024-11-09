package com.example.weather_compose.test

import com.example.weather_compose.data.DailyForecastResponse
import com.example.weather_compose.utilities.formatTime
import org.junit.Assert.assertEquals
import org.junit.Test

class FormatTimeUtilTest {

    private val dailyForecast = DailyForecastResponse(
        date = "2024-06-16",
        sunrise = "05:30:00",   // Mock sunrise time
        sunset = "20:30:00"   // Mock sunset time
    )

    @Test
    fun testFormatTimeForSunrise() {
        val result = formatTime("05:30:00", dailyForecast)
        assertEquals("sunrise", result)
    }

    @Test
    fun testFormatTimeForSunset() {
        val result = formatTime("20:30:00", dailyForecast)
        assertEquals("sunset", result)
    }

    @Test
    fun testFormatTimeForMorning() {
        val result = formatTime("09:45:00", dailyForecast)
        assertEquals("9 a.m.", result)
    }

    @Test
    fun testFormatTimeForAfternoon() {
        val result = formatTime("13:15:00", dailyForecast)
        assertEquals("1 p.m.", result)
    }

    @Test
    fun testFormatTimeForEvening() {
        val result = formatTime("18:45:00", dailyForecast)
        assertEquals("6 p.m.", result)
    }

    @Test
    fun testFormatTimeForMidnight() {
        val result = formatTime("00:00:00", dailyForecast)
        assertEquals("12 a.m.", result)
    }

    @Test
    fun testFormatTimeForNoon() {
        val result = formatTime("12:00:00", dailyForecast)
        assertEquals("12 p.m.", result)
    }
}
