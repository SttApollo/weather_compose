package com.example.weather_compose.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weather_compose.data.WeatherResponse
import com.example.weather_compose.ui.theme.GradientOverlay
import com.example.weather_compose.utilities.getWeatherIcon

@Composable
fun WeatherDetails(weather: WeatherResponse) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        val iconResId = weather.days[0].icon?.let { getWeatherIcon(it) }

        Text(
            text = weather.address,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineMedium.copy(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            ),
            color = GradientOverlay,
        )

        iconResId?.let { painterResource(id = it) }?.let {
            Image(
                painter = it,
                contentDescription = "Weather Icon",
                modifier = Modifier.size(100.dp)
            )
        }

        Text(
            text = "${weather.days[0].temp}°F",
            textAlign = TextAlign.Center,
            color = GradientOverlay
        )
        weather.days[0].conditions?.let {
            Text(
                text = it,
                textAlign = TextAlign.Center,
                color = GradientOverlay
            )
        }

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "High",
                    color = GradientOverlay
                )
                Text(
                    text = "${weather.days[0].tempMax}°F",
                    color = GradientOverlay
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Low",
                    color = GradientOverlay
                )
                Text(
                    text = "${weather.days[0].tempMin}°F",
                    color = GradientOverlay
                )
            }
        }
    }
}
