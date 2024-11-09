package com.example.weather_compose.ui.compose

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weather_compose.BuildConfig
import com.example.weather_compose.ui.compose.home.HomeScreen
import com.example.weather_compose.ui.compose.weatherdetail.WeatherDetailView

@Composable
fun WeatherApp() {
    val navController = rememberNavController()
    WeatherNavHost(
        navController = navController)

}

@Composable
fun WeatherNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(route = Screen.Home.route) {
            HomeScreen(navController = navController, onWeatherClick = { city, country ->
                navController.navigate(Screen.WeatherDetail.createRoute(city.toString(), country.toString()))
            })
        }
        composable(
            route = Screen.WeatherDetail.route,
            arguments = Screen.WeatherDetail.navArguments
        ) { backStackEntry ->
            val city = backStackEntry.arguments?.getString("city") ?: ""
            val country = backStackEntry.arguments?.getString("country") ?: ""
            WeatherDetailView(
                city = city,
                country = country,
                apiKey = BuildConfig.VISUAL_CROSSING_API_KEY,
                onBackClick = { navController.navigateUp() }
            )
        }
    }
}