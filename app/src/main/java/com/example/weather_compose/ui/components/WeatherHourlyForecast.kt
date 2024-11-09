package com.example.weather_compose.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weather_compose.data.DailyForecastResponse
import com.example.weather_compose.data.HourlyForecastResponse
import com.example.weather_compose.data.WeatherResponse
import com.example.weather_compose.ui.theme.DarkPurple
import com.example.weather_compose.ui.theme.LightPurple
import com.example.weather_compose.ui.theme.MediumPurple
import com.example.weather_compose.ui.theme.OffWhite
import com.example.weather_compose.ui.theme.Weather_ComposeTheme
import com.example.weather_compose.utilities.formatTime
import com.example.weather_compose.utilities.getWeatherIcon

@Composable
fun WeatherHourlyForecast(weather: WeatherResponse) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        DarkPurple.copy(alpha = 0.9f),
                        MediumPurple.copy(alpha = 0.8f),
                        LightPurple.copy(alpha = 0.6f)
                    ),
                    startX = 0f,
                    endX = Float.POSITIVE_INFINITY
                ),
                shape = RoundedCornerShape(16.dp)
            )
            .padding(16.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "Hourly Forecast",
                style = MaterialTheme.typography.titleMedium,
                color = OffWhite
            )
            HorizontalDivider(thickness = 0.6.dp, color = Color.White)
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(weather.days.firstOrNull()?.hours ?: emptyList()) { hourlyForecast ->
                    HourlyForecastItem(hourlyForecast, weather.days.first())
                }
            }
        }
    }
}

@Composable
fun HourlyForecastItem(hourlyForecast: HourlyForecastResponse, dailyForecast: DailyForecastResponse) {
    val iconResId = getWeatherIcon(hourlyForecast.icon)
    var isClicked by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(if (isClicked) 1.2f else 1f, label = "")
    val displayTime = formatTime(hourlyForecast.time, dailyForecast)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable { isClicked = !isClicked }
            .padding(vertical = 5.dp)

    ) {
        Text(
            text = displayTime,
            style = MaterialTheme.typography.bodySmall,
            color = OffWhite
        )

        Image(
            painter = painterResource(id = iconResId),
            contentDescription = "Weather Icon",
            modifier = Modifier
                .size(20.dp)
                .scale(scale)
        )

        Text(
            text = "${(hourlyForecast.temperature).toInt()} F",
            style = MaterialTheme.typography.bodyMedium,
            color = OffWhite
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewWeatherHourlyForecast() {
    // Mock data for hourly forecast
    val mockWeatherResponse = WeatherResponse(
        latitude = 45.5017,
        longitude = -73.5673,
        resolvedAddress = "Montreal, Canada",
        address = "Montreal, Canada",
        timezone = "America/Toronto",
        description = "Clear conditions throughout the day.",
        days = listOf(
            DailyForecastResponse(
                date = "2024-06-16",
                tempMax = 24.0,
                tempMin = 18.0,
                temp = 20.0,
                icon = "clear-day",
                conditions = "Clear",
                hours = listOf(
                    HourlyForecastResponse(
                        time = "08:00:00",
                        datetimeEpoch = 1718510400,
                        temperature = 18.0,
                        feelsLike = 18.0,
                        humidity = 75.0,
                        dew = 10.0,
                        precip = 0.0,
                        precipProb = 0.0,
                        snow = 0.0,
                        snowDepth = 0.0,
                        precipType = null,
                        windGust = 10.0,
                        windSpeed = 5.0,
                        windDir = 90.0,
                        pressure = 1015.0,
                        visibility = 10.0,
                        cloudCover = 20.0,
                        solarRadiation = 200.0,
                        solarEnergy = 5.0,
                        uvIndex = 3,
                        severeRisk = 0,
                        conditions = "Clear",
                        icon = "clear-day",
                        stations = null,
                        source = "mockSource"
                    ),
                    HourlyForecastResponse(
                        time = "12:00:00",
                        datetimeEpoch = 1718524800,
                        temperature = 22.0,
                        feelsLike = 22.0,
                        humidity = 60.0,
                        dew = 12.0,
                        precip = 0.0,
                        precipProb = 0.0,
                        snow = 0.0,
                        snowDepth = 0.0,
                        precipType = null,
                        windGust = 15.0,
                        windSpeed = 7.0,
                        windDir = 100.0,
                        pressure = 1012.0,
                        visibility = 10.0,
                        cloudCover = 10.0,
                        solarRadiation = 250.0,
                        solarEnergy = 6.0,
                        uvIndex = 5,
                        severeRisk = 0,
                        conditions = "Sunny",
                        icon = "sunny",
                        stations = null,
                        source = "mockSource"
                    ),
                    HourlyForecastResponse(
                        time = "04:00:00",
                        datetimeEpoch = 1718539200,
                        temperature = 24.0,
                        feelsLike = 24.0,
                        humidity = 55.0,
                        dew = 13.0,
                        precip = 0.0,
                        precipProb = 0.0,
                        snow = 0.0,
                        snowDepth = 0.0,
                        precipType = null,
                        windGust = 12.0,
                        windSpeed = 8.0,
                        windDir = 110.0,
                        pressure = 1010.0,
                        visibility = 10.0,
                        cloudCover = 5.0,
                        solarRadiation = 300.0,
                        solarEnergy = 7.0,
                        uvIndex = 6,
                        severeRisk = 0,
                        conditions = "Partly Cloudy",
                        icon = "partly-cloudy-day",
                        stations = null,
                        source = "mockSource"
                    )
                )
            )
        )
    )

    Weather_ComposeTheme {
        WeatherHourlyForecast(weather = mockWeatherResponse)
    }
}

