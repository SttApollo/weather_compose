package com.example.weather_compose.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
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
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weather_compose.ui.theme.DarkPurple
import com.example.weather_compose.ui.theme.LightPurple
import com.example.weather_compose.ui.theme.MediumPurple
import com.example.weather_compose.ui.theme.OffWhite
import com.example.weather_compose.ui.theme.Weather_ComposeTheme
import com.example.weather_compose.utilities.getWeatherIcon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherWidget(
    temperature: String,
    tempMax: String,
    tempMin: String,
    resolvedAddress: String,
    icon: String,
    conditions: String,
    onClick: () -> Unit,
    onDelete: () -> Unit,
) {
    val iconResId = getWeatherIcon(icon)
    var isClicked by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(if (isClicked) 1.2f else 1f, label = "")

    val dismissState = rememberSwipeToDismissBoxState(
        confirmValueChange = { newValue ->
            if (newValue == SwipeToDismissBoxValue.EndToStart || newValue == SwipeToDismissBoxValue.StartToEnd) {
                onDelete()
                true
            } else {
                false
            }
        }
    )

    SwipeToDismissBox(
        state = dismissState,
        backgroundContent = {},
        enableDismissFromStartToEnd = true,
        enableDismissFromEndToStart = true,
        content = {
            Card(
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .fillMaxWidth()
                    .clickable { onClick() },
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
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(
                            modifier = Modifier.weight(1f),
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = temperature,
                                style = MaterialTheme.typography.displayLarge.copy(
                                    fontSize = 64.sp,
                                    fontWeight = FontWeight.Bold
                                ),
                                color = OffWhite
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Row {
                                Text(
                                    text = "H: $tempMax",
                                    style = MaterialTheme.typography.labelLarge,
                                    color = OffWhite
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "L: $tempMin",
                                    style = MaterialTheme.typography.labelLarge,
                                    color = OffWhite
                                )
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = resolvedAddress,
                                fontSize = 14.sp,
                                color = OffWhite,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                modifier = Modifier.fillMaxWidth(0.8f)


                            )
                        }
                        Column(
                            modifier = Modifier.weight(0.5f),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Image(
                                painter = painterResource(id = iconResId),
                                contentDescription = "Weather Icon",
                                modifier = Modifier
                                    .size(80.dp)
                                    .scale(scale)
                                    .pointerInput(Unit) {
                                        detectTapGestures(
                                            onTap = {
                                                isClicked = !isClicked
                                            }
                                        )
                                    },
                                contentScale = ContentScale.Fit
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = conditions,
                                fontSize = 12.sp,
                                color = OffWhite,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    )
}


@Preview(showBackground = true)
@Composable
fun PreviewWeatherWidget() {
    Weather_ComposeTheme {
        WeatherWidget(
            temperature = "19°C",
            tempMax = "24°C",
            tempMin = "18°C",
            resolvedAddress = "Montreal, Canada",
            icon = "https://openweathermap.org/img/wn/10d@2x.png",
            conditions = "Mid Rain",
            onClick = {},
            onDelete = {}
        )
    }
}

