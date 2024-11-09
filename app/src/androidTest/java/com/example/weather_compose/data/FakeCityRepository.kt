package com.example.weather_compose.data

import android.util.Log
import com.example.weather_compose.models.City
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FakeCityRepository @Inject constructor() : CityRepository {

    private val cityList = mutableListOf<City>()
    private var isPreloaded = false

    // Function to preload test data into the fake repository
    override suspend fun loadCitiesFromAssets() {
        if (!isPreloaded) {
            cityList.addAll(
                listOf(
                    City("London", 51.5074, -0.1278, "United Kingdom"),
                    City("Londonderry", 54.9976, -7.3086, "United Kingdom"),
                    City("New York", 40.7128, -74.0060, "United States")
                )
            )
            isPreloaded = true
            Log.d("FakeCityRepository", "Preloaded ${cityList.size} cities for testing")
        } else {
            Log.d("FakeCityRepository", "Cities already preloaded, skipping...")
        }
    }

    override suspend fun searchCities(cityQuery: String, countryQuery: String): List<City> {
        val result = cityList.filter {
            it.name.contains(cityQuery, ignoreCase = true) &&
                    it.country.contains(countryQuery, ignoreCase = true)
        }
        Log.d("FakeCityRepository", "Search results for '$cityQuery, $countryQuery': $result")
        return result
    }
}
