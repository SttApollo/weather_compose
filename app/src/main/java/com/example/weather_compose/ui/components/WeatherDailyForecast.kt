package com.example.weather_compose.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import com.example.weather_compose.data.WeatherResponse
import com.example.weather_compose.ui.theme.DarkPurple
import com.example.weather_compose.ui.theme.LightPurple
import com.example.weather_compose.ui.theme.MediumPurple
import com.example.weather_compose.ui.theme.OffWhite
import com.example.weather_compose.ui.theme.Weather_ComposeTheme
import com.example.weather_compose.utilities.convertDate
import com.example.weather_compose.utilities.getWeatherIcon

@Composable
fun WeatherDailyForecast(weather: WeatherResponse) {
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
        Column {
            Text(
                text = "10-Day Forecast",
                style = MaterialTheme.typography.titleMedium,
                color = OffWhite,
                modifier = Modifier.align(Alignment.Start)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                weather.days.take(10).forEach { dailyForecast ->
                    DailyForecastItem(dailyForecast)
                    HorizontalDivider(thickness = 0.6.dp, color = Color.White)
                }
            }
        }
    }
}



@Composable
fun DailyForecastItem(dailyForecast: DailyForecastResponse) {
    val iconResId = dailyForecast.icon?.let { getWeatherIcon(it) }
    var isClicked by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(if (isClicked) 1.2f else 1f, label = "")
    val displayDate = convertDate(dailyForecast.date)

    Row(
        modifier = Modifier
            .clickable { isClicked = !isClicked }
            .padding(vertical = 2.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = displayDate,
            style = MaterialTheme.typography.bodyLarge,
            color = OffWhite,
            maxLines = 1,
            modifier = Modifier.weight(2f)
        )

        iconResId?.let { painterResource(id = it) }?.let {
            Image(
                painter = it,
                contentDescription = "Weather Icon",
                modifier = Modifier
                    .size(30.dp)
                    .scale(scale)
                    .weight(1f)
            )
        }

        Text(
            text = "${dailyForecast.tempMax}",
            style = MaterialTheme.typography.bodyMedium,
            color = OffWhite,
            modifier = Modifier.weight(1f)
        )

        Column(
            modifier = Modifier
                .weight(1.5f)
                .padding(end = 8.dp)
        ) {
            dailyForecast.temp?.let {
                dailyForecast.tempMin?.let { it1 ->
                    dailyForecast.tempMax?.let { it2 ->
                        TemperatureThermometer(
                            currentTemp = it,
                            lowTemp = it1,
                            highTemp = it2,
                        )
                    }
                }
            }
        }

        Text(
            text = " ${dailyForecast.tempMin}",
            style = MaterialTheme.typography.bodyMedium,
            color = OffWhite,
            modifier = Modifier.weight(1f)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewDailyForecastItem() {
    val dailyForecast = DailyForecastResponse(
        date = "2024-06-16",
        datetimeEpoch = 1718510400,
        tempMax = 24.0,
        tempMin = 18.0,
        temp = 19.0,
        feelsLikeMax = 24.0,
        feelsLikeMin = 18.0,
        feelsLike = 19.0,
        dew = 10.0,
        humidity = 65.0,
        precip = 0.0,
        precipProb = 0.0,
        precipCover = 0.0,
        precipType = null,
        snow = 0.0,
        snowDepth = 0.0,
        windGust = 10.0,
        windSpeed = 5.0,
        windDir = 90.0,
        pressure = 1015.0,
        cloudCover = 20.0,
        visibility = 10.0,
        solarRadiation = 200.0,
        solarEnergy = 5.0,
        uvIndex = 5,
        severeRisk = 0,
        sunrise = "05:30 AM",
        sunriseEpoch = 1718530534,
        sunset = "08:30 PM",
        sunsetEpoch = 1718586089,
        moonPhase = 0.5,
        conditions = "Clear",
        description = "Clear conditions throughout the day.",
        icon = "clear-day",
        stations = null,
        source = "comb",
        hours = emptyList()
    )

    Weather_ComposeTheme {
        DailyForecastItem(dailyForecast = dailyForecast)
    }
}
