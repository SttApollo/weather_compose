package com.example.weather_compose.ui.compose

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class Screen(val route: String) {
    data object Home : Screen("home")

    data object WeatherDetail : Screen("weatherDetail/{city},{country}") {

        fun createRoute(city: String, country: String) = "weatherDetail/$city,$country"
        val navArguments: List<NamedNavArgument> = listOf(
            navArgument("city") { type = NavType.StringType },
            navArgument("country") { type = NavType.StringType }
        )
    }
}
