package com.example.weather_compose.api

import com.example.weather_compose.data.WeatherResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


//https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/toronto/?key=MTMRN75UVB9QLAZUFZ5YPTG4K
interface WeatherService {
    @GET("VisualCrossingWebServices/rest/services/timeline/{city},{country}")
    suspend fun getWeather(
        @Path("city") city: String,
        @Path("country") country: String,
        @Query("key") apiKey: String
    ): WeatherResponse
}