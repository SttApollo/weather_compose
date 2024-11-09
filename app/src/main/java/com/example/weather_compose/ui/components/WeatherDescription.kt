package com.example.weather_compose.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.weather_compose.data.WeatherResponse
import com.example.weather_compose.ui.theme.DarkPurple
import com.example.weather_compose.ui.theme.LightPurple
import com.example.weather_compose.ui.theme.MediumPurple
import com.example.weather_compose.ui.theme.OffWhite

@Composable
fun WeatherDescription(weather: WeatherResponse) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        )
    ) {
        Box(
            modifier = Modifier
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            DarkPurple.copy(alpha = 0.9f),
                            MediumPurple.copy(alpha = 0.8f),
                            LightPurple.copy(alpha = 0.6f)
                        ),
                        startX = 0f,
                        endX = Float.POSITIVE_INFINITY
                    )
                )
                .padding(16.dp)
        ) {
            Column {
                Text(
                    text = weather.description,
                    style = MaterialTheme.typography.bodyLarge,
                    color = OffWhite
                )
            }
        }
    }
}
