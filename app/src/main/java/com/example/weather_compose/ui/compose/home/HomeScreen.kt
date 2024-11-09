package com.example.weather_compose.ui.compose.home

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.weather_compose.ui.components.SearchBar
import com.example.weather_compose.ui.components.WeatherWidget
import com.example.weather_compose.viewmodels.CitySearchViewModel
import com.example.weather_compose.viewmodels.LocationViewModel
import com.example.weather_compose.viewmodels.WeatherItemListViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState


@OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
    onWeatherClick: (String, String) -> Unit,
    citySearchViewModel: CitySearchViewModel = hiltViewModel(),
    weatherItemListViewModel: WeatherItemListViewModel = hiltViewModel(),
    locationViewModel: LocationViewModel = hiltViewModel()
) {
    val cities by citySearchViewModel.cities.collectAsState()
    val weatherItems by weatherItemListViewModel.weatherItems.collectAsStateWithLifecycle()


    // Observe the location permissions state
    val locationPermissions = rememberMultiplePermissionsState(
        permissions = listOf(
            android.Manifest.permission.ACCESS_COARSE_LOCATION,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        )
    )

    // Observe the geocoded location from the LocationViewModel
    val geocodedLocation by locationViewModel.geocodedLocation.collectAsState()


    //When the app launches for the first time
        // Track permission changes and get location if granted
    LaunchedEffect(locationPermissions.allPermissionsGranted) {
        if (locationPermissions.allPermissionsGranted) {
            Log.d("HomeScreen", "Permissions granted, fetching location.")
            locationViewModel.getCurrentLocation()

        } else {
            Log.d("HomeScreen", "Requesting location permissions.")
            locationPermissions.launchMultiplePermissionRequest()
        }
    }

    // This will only log when geocodedLocation changes
    LaunchedEffect(geocodedLocation) {
        if (geocodedLocation != null) {
            citySearchViewModel.searchCities(geocodedLocation!!.first)
        }
    }


    LaunchedEffect(Unit) {
        weatherItemListViewModel.refreshWeatherItems()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Weather") }
            )
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .padding(horizontal = 16.dp)
                    .fillMaxSize()
            ) {
                // Use geocoded location if available to populate the search bar
                val initialText = geocodedLocation?.first ?: ""
                Log.d("HomeScreen", "Initial Text: $initialText")

                SearchBar(
                    hint = "Search for a city",
                    initialText = initialText,
                    onTextChange = { query -> citySearchViewModel.searchCities(query) },
                    onSearchClicked = { query ->
                        // Handle direct click on initial text
                        if (query == initialText && geocodedLocation != null) {
                            onWeatherClick(geocodedLocation!!.first, geocodedLocation!!.second)
                        } else {
                            val selectedCity = cities.find { it.name.contains(query.split(",")[0].trim(), ignoreCase = true) }
                            if (selectedCity != null) {
                                onWeatherClick(selectedCity.name, selectedCity.country)
                            }
                        }
                    },
                    suggestions = cities,

                )

                LazyColumn {
                    items(weatherItems, key = {it.weatherItemId}) { weatherItem ->
                        WeatherWidget(
                            temperature = weatherItem.temperature,
                            tempMax = weatherItem.tempMax,
                            tempMin = weatherItem.tempMin,
                            resolvedAddress = weatherItem.resolvedAddress,
                            icon = weatherItem.icon,
                            conditions = weatherItem.conditions,
                            onClick = {
                                val city = weatherItem.resolvedAddress.split(",")[0].trim()
                                val country = weatherItem.resolvedAddress.split(",")[1].trim()
                                onWeatherClick(city, country)
                            },
                            onDelete = {
                                weatherItemListViewModel.deleteWeatherItem(weatherItem)
                            }
                        )
                    }
                }// end of LazyColumn

            }
        }//end of content
    )//end of Scaffold
}
