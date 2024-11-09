package com.example.weather_compose.test

import com.example.weather_compose.api.WeatherService
import com.example.weather_compose.data.DailyForecastResponse
import com.example.weather_compose.data.WeatherDao
import com.example.weather_compose.data.WeatherRepository
import com.example.weather_compose.data.WeatherResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.doThrow
import org.mockito.kotlin.anyOrNull
import org.mockito.kotlin.mock

