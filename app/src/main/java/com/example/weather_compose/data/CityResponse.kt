package com.example.weather_compose.data

import com.google.gson.annotations.SerializedName

data class CityResponse(
    val id: Int,
    val name: String,
    val state_id: Int,
    val state_code: String,
    val state_name: String,
    val country_id: Int,
    val country_code: String,
    val country_name: String,
    val latitude: String,
    val longitude: String,
    val wikiDataId: String?
)