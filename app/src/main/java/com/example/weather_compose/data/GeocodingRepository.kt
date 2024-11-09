package com.example.weather_compose.data


import com.example.weather_compose.models.City
import javax.inject.Inject
import javax.inject.Singleton

//@Singleton
//class GeocodingRepository @Inject constructor(
//    private val geocodingService: GeocodingService
//) {
//    suspend fun searchCities(query: String): List<City> {
//        val geocodingResponse = geocodingService.getCities(query)
//        return geocodingResponse.data.map { cityResponse ->
//            City(
//                name = cityResponse.name,
//                lat = cityResponse.geoCode?.latitude ?: 0.0,
//                lon = cityResponse.geoCode?.longitude ?: 0.0,
//                country = cityResponse.country
//            )
//        }
//    }
//}
