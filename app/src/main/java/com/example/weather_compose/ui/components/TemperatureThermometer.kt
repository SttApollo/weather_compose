package com.example.weather_compose.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.unit.dp
import com.example.weather_compose.ui.theme.Purple80


@Composable
fun TemperatureThermometer(
    currentTemp: Double,
    lowTemp: Double,
    highTemp: Double,
) {
    // Calculate the percentage position of the current temperature between low and high
    val position = ((currentTemp - lowTemp) / (highTemp - lowTemp)).coerceIn(0.0, 1.0)

    // Define a custom Layout to control the positioning of the marker
    Layout(
        content = {
            // Background line representing the temperature range
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(4.dp)
                    .background(Color.White)
            )
            // Marker representing the current temperature
            Box(
                modifier = Modifier
                    .width(10.dp)
                    .height(20.dp)
                    .background(Purple80)
            )
        },
        modifier = Modifier
            .height(4.dp)
            .padding(horizontal = 10.dp)
    ) { measurables, constraints ->
        // Measure the children
        val linePlaceable = measurables[0].measure(constraints)
        val markerPlaceable = measurables[1].measure(constraints)

        // Calculate the position of the marker
        val markerPosition = (constraints.maxWidth * position).toInt()

        //Defines the size of the custom layout
        layout(constraints.maxWidth, constraints.maxHeight) {
            // Place the background line
            linePlaceable.placeRelative(0, (constraints.maxHeight - linePlaceable.height) / 2)
            // Place the marker
            markerPlaceable.placeRelative(markerPosition - (markerPlaceable.width / 2), 0)
        }
    }
}
