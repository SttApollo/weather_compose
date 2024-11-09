package com.example.weather_compose.ui.compose.weatherdetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.weather_compose.data.DailyForecastResponse
import com.example.weather_compose.data.WeatherResponse
import com.example.weather_compose.ui.components.WeatherDailyForecast
import com.example.weather_compose.ui.components.WeatherDescription
import com.example.weather_compose.ui.components.WeatherDetails
import com.example.weather_compose.ui.components.WeatherHourlyForecast
import com.example.weather_compose.ui.theme.Weather_ComposeTheme
import com.example.weather_compose.viewmodels.WeatherDetailViewModel
import com.example.weather_compose.viewmodels.WeatherItemListViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherDetailView(
    city: String,
    country: String,
    apiKey: String,
    onBackClick: () -> Unit,
    weatherDetailViewModel: WeatherDetailViewModel = hiltViewModel(),
    weatherItemListViewModel: WeatherItemListViewModel = hiltViewModel()
) {
    val weather by weatherDetailViewModel.weather.collectAsState()
    val isAddressSaved by weatherDetailViewModel.isAddressSaved.observeAsState(false)
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    // Ensure we check if the address is saved when the composable is first launched
    LaunchedEffect(city, country) {
        weatherDetailViewModel.checkAddressSaved(city, country)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Weather Details") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(
                        onClick = {
                            weatherDetailViewModel.saveAddress {
                                scope.launch {
                                    snackbarHostState.showSnackbar("Address saved!")
                                    weatherItemListViewModel.refreshWeatherItems()
                                }
                            }
                        },
                        enabled = !isAddressSaved // Disable button if address is already saved
                    ) {
                        Icon(Icons.Default.Add, contentDescription = "Save")
                    }
                }
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) },
        content = { padding ->
            Box(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
            ) {
                LazyColumn(
                    modifier = Modifier
                        .padding(horizontal = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    item {
                        weather?.let {
                            WeatherDetails(weather = it)
                        } ?: run {
                            CircularProgressIndicator()
                        }
                    }
                    item {
                        weather?.let {
                            WeatherDescription(weather = it)
                        }
                    }
                    item {
                        weather?.let {
                            WeatherHourlyForecast(weather = it)
                        }
                    }
                    item {
                        weather?.let {
                            WeatherDailyForecast(weather = it)
                        }
                    }
                }
            }
        }
    )
}



@Preview(showBackground = true)
@Composable
fun PreviewWeatherDetailView() {
    val mockWeather = WeatherResponse(
        latitude = 45.5017,
        longitude = -73.5673,
        resolvedAddress = "Montreal, Canada",
        address = "Montreal, Canada",
        timezone = "America/Toronto",
        description = "Clear conditions throughout the day.",
        days = listOf(
            DailyForecastResponse(
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
        )
    )

    Weather_ComposeTheme {
        WeatherDetailView(
            city = "Montreal",
            country = "Canada",
            apiKey = "your_api_key",
            onBackClick = {}
        )
    }
}
